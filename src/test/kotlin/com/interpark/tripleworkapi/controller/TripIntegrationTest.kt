package com.interpark.tripleworkapi.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.param.CityParam
import com.interpark.tripleworkapi.domain.param.PlanParam
import com.interpark.tripleworkapi.domain.param.TripParam
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TripIntegrationTest(
    private val mockMvc: MockMvc
) {
    @Test
    fun `여행 create`() {
        val tripParam = TripParam(
            title = "서울로 떠나는 신나는 여행~",
            cityId = 1,
            plan = PlanParam(
                startedAt = LocalDate.now(),
                endedAt = LocalDate.now()
            )
        )

        val tripParamJson = jacksonObjectMapper().writeValueAsString(tripParam)
        mockMvc.perform(
            MockMvcRequestBuilders.put("/v1/trips").content(tripParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)

    }
}