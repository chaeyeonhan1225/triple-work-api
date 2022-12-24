package com.interpark.tripleworkapi.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.param.CityParam
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CityIntegrationTest(
    private val mockMvc: MockMvc
) {
    @Test
    fun `도시 create`() {
        val cityParam = CityParam(
            name = "Seoul",
            countryCode = "KR"
        )
        val cityParamJson = jacksonObjectMapper().writeValueAsString(cityParam)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/cities").content(cityParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andExpectAll(
            jsonPath("\$.name").value(cityParam.name),
            jsonPath("\$.countryCode").value(cityParam.countryCode),
            jsonPath("\$.status").value(CommonState.ACTIVE.label)
        ).andDo(
            MockMvcResultHandlers.print()
        )
    }

    @Test
    fun `도시 create - 2`() {
        val cityParam = CityParam(
            name = "Busan",
            countryCode = "KR"
        )
        val cityParamJson = jacksonObjectMapper().writeValueAsString(cityParam)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/cities").content(cityParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andExpectAll(
            jsonPath("\$.name").value(cityParam.name),
            jsonPath("\$.countryCode").value(cityParam.countryCode),
            jsonPath("\$.status").value(CommonState.ACTIVE.label)
        ).andDo(
            MockMvcResultHandlers.print()
        )
    }

    @Test
    fun `도시 update - 1`() {
        val cityUpdateParam = CityParam(
            name = "Seoul - 수정",
            countryCode = "KR"
        )
        val cityUpdateParamJson = jacksonObjectMapper().writeValueAsString(cityUpdateParam)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/v1/cities/1").content(cityUpdateParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andExpectAll(
            jsonPath("\$.id").value(1),
            jsonPath("\$.name").value(cityUpdateParam.name),
            jsonPath("\$.countryCode").value(cityUpdateParam.countryCode),
            jsonPath("\$.status").value(CommonState.ACTIVE.label)
        ).andDo(
            MockMvcResultHandlers.print()
        )
    }

    @Test
    fun `도시 update - 2 Error(존재하지 않는 도시 조회)`() {
        val cityId = "1231241432525"
        val cityUpdateParam = CityParam(
            name = "Seoul - 수정",
            countryCode = "KR"
        )
        val cityUpdateParamJson = jacksonObjectMapper().writeValueAsString(cityUpdateParam)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/v1/cities/$cityId").content(cityUpdateParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError)
            .andDo(
            MockMvcResultHandlers.print()
        )
    }

    @Test
    fun `도시 delete - 1`() {
        val cityParam = CityParam(
            name = "Seoul",
            countryCode = "KR"
        )
        val cityParamJson = jacksonObjectMapper().writeValueAsString(cityParam)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/cities").content(cityParamJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andExpectAll(
            jsonPath("\$.id").value(6),
            jsonPath("\$.name").value(cityParam.name),
            jsonPath("\$.countryCode").value(cityParam.countryCode),
            jsonPath("\$.status").value(CommonState.ACTIVE.label)
        ).andDo(
            MockMvcResultHandlers.print()
        )

        val cityId = CityId(6)
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/v1/cities/${cityId.value}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk).andDo(
            MockMvcResultHandlers.print()
        )
    }

    @Test
    fun `도시 delete - 2(도시가 존재하지 않음)`() {
        val cityId = CityId(11231241241234)
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/v1/cities/${cityId.value}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError).andDo(
            MockMvcResultHandlers.print()
        )
    }
}