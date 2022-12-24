package com.interpark.tripleworkapi.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.param.CityParam
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
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
            jsonPath("\$.id").value(1),
            jsonPath("\$.name").value(cityParam.name),
            jsonPath("\$.countryCode").value(cityParam.countryCode),
            jsonPath("\$.status").value(CommonState.ACTIVE.label)
        ).andDo(
            MockMvcResultHandlers.print()
        )
    }
}