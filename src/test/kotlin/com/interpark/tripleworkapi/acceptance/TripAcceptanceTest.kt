package com.interpark.tripleworkapi.acceptance

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.trip.PlanParam
import com.interpark.tripleworkapi.domain.trip.TripParam
import io.mockk.InternalPlatformDsl.toStr
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate


@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql("classpath:test/data.sql")
class TripAcceptanceTest(
    private val mockMvc: MockMvc
) {
    @Test
    fun `여행을 생성한다`() {
        val tripParam = TripParam(
            title = "서울로 떠나는 신나는 여행~",
            cityId = 1,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        val tripParamJson = jacksonObjectMapper().registerModule(
            JavaTimeModule()
        ).writeValueAsString(tripParam)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/trips").content(tripParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `여행을 생성한다(존재하지 않는 도시)`() {
        val tripParam = TripParam(
            title = "서울로 떠나는 신나는 여행~",
            cityId = 123123123123123,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        val tripParamJson = jacksonObjectMapper().registerModule(
            JavaTimeModule()
        ).writeValueAsString(tripParam)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/trips").content(tripParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `여행을 생성한다(존재하지 않는 유저)`() {
        val tripParam = TripParam(
            title = "서울로 떠나는 신나는 여행~",
            cityId = 1,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 123123123
        )

        val tripParamJson = jacksonObjectMapper().registerModule(
            JavaTimeModule()
        ).writeValueAsString(tripParam)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/trips").content(tripParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `여행을 수정한다(id=1)`() {
        val tripParam = TripParam(
            title = "서울로 떠나는 신나는 여행~",
            cityId = 1,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        createTestTrip(tripParam)

        val tripUpdateParam = TripParam(
            title = "서울로 떠나는 신나는 여행~ - 수정",
            cityId = 2,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        val tripUpdateParamJson = jacksonObjectMapper().registerModule(
            JavaTimeModule()
        ).writeValueAsString(tripUpdateParam)

        val tripId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.put("/v1/trips/$tripId").content(tripUpdateParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("\$.id").value(1),
                MockMvcResultMatchers.jsonPath("\$.title").value(tripUpdateParam.title),
                MockMvcResultMatchers.jsonPath("\$.status").value(CommonState.ACTIVE.label),
                MockMvcResultMatchers.jsonPath("\$.cityId").value(tripUpdateParam.cityId)
            )
    }

    @Test
    fun `여행을 수정(id=1)한다(존재하지 않는 도시)`() {
        val tripParam = TripParam(
            title = "서울로 떠나는 신나는 여행~",
            cityId = 1,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        createTestTrip(tripParam)

        val tripUpdateParam = TripParam(
            title = "서울로 떠나는 신나는 여행~ - 수정",
            cityId = 123123123123,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        val tripUpdateParamJson = jacksonObjectMapper().registerModule(
            JavaTimeModule()
        ).writeValueAsString(tripUpdateParam)

        val tripId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.put("/v1/trips/$tripId").content(tripUpdateParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `여행을 수정(id=1)한다(존재하지 않는 여행)`() {
        val tripUpdateParam = TripParam(
            title = "서울로 떠나는 신나는 여행~ - 수정",
            cityId = 1,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        val tripUpdateParamJson = jacksonObjectMapper().registerModule(
            JavaTimeModule()
        ).writeValueAsString(tripUpdateParam)

        val tripId = 1123124124123

        mockMvc.perform(
            MockMvcRequestBuilders.put("/v1/trips/$tripId").content(tripUpdateParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `여행을 삭제한다`() {
        val tripParam = TripParam(
            title = "서울로 떠나는 신나는 여행~",
            cityId = 1,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        createTestTrip(tripParam)
        val tripId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/v1/trips/$tripId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `존재하지 않는 여행을 삭제한다`() {
        val tripId = 123123123123

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/v1/trips/$tripId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun `여행(id=1)을 조회한다`() {
        val tripParam = TripParam(
            title = "서울로 떠나는 신나는 여행~",
            cityId = 1,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            ),
            userId = 1
        )

        createTestTrip(tripParam)
        val tripId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/trips/$tripId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("\$.id").value(1),
                MockMvcResultMatchers.jsonPath("\$.title").value(tripParam.title),
                MockMvcResultMatchers.jsonPath("\$.status").value(CommonState.ACTIVE.label),
                MockMvcResultMatchers.jsonPath("\$.city.id").value(tripParam.cityId),
                MockMvcResultMatchers.jsonPath("\$.plan.startedAt").value(tripParam.plan.startedAt.toStr()),
                MockMvcResultMatchers.jsonPath("\$.plan.endedAt").value(tripParam.plan.endedAt.toStr())
            )
    }

    private fun createTestTrip(param: TripParam) {
        val tripParamJson = jacksonObjectMapper().registerModule(
            JavaTimeModule()
        ).writeValueAsString(param)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/trips").content(tripParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }
}