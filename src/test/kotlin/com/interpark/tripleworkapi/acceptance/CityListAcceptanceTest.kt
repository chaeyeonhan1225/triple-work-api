package com.interpark.tripleworkapi.acceptance

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


@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CityListAcceptanceTest(
    private val mockMvc: MockMvc
) {
    @Test
    @Sql("classpath:test/city-list-case1.sql")
    fun `여행중인 도시(id= 6)는 제일 앞으로 정렬됨`() {
        val testUserId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/cities?userId=$testUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("\$[0].id").value(6),
            )
    }

    @Test
    @Sql("classpath:test/city-list-case2.sql")
    fun `여행중인 도시(id= 2, 3)는 제일 앞으로 정렬되고, 여행 일자가 빠른 순서대로 정렬된다`() {
        val testUserId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/cities?userId=$testUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("\$[0].id").value(3),
                MockMvcResultMatchers.jsonPath("\$[1].id").value(2),
            )
    }

    @Test
    @Sql("classpath:test/city-list-case3.sql")
    fun `여행중인 도시 X, 여행이 예정된 도시X 최근에 생성된 도시 순서(id= 7, 8)대로 정렬된다`() {
        val testUserId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/cities?userId=$testUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("\$[0].id").value(7),
                MockMvcResultMatchers.jsonPath("\$[1].id").value(8),
            )
    }

    @Test
    @Sql("classpath:test/city-list-case4.sql")
    fun `여행중인 도시 X, 여행이 예정된 도시X 최근에 생성된 도시(id = 7, 8), 최근에 조회된 도시(id = 3)`() {
        val testUserId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/cities?userId=$testUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("\$[0].id").value(7),
                MockMvcResultMatchers.jsonPath("\$[1].id").value(8),
                MockMvcResultMatchers.jsonPath("\$[2].id").value(3)
            )
    }

    @Test
    @Sql("classpath:test/city-list-case5.sql")
    fun `여행중인 도시 X, 여행이 예정된 도시X 최근에 생성된 도시(id = 7, 8), 최근에 조회된 도시(id = 1, 2, 3)은 최근 조회 순으로 정렬된다`() {
        val testUserId = 1

        mockMvc.perform(
            MockMvcRequestBuilders.get("/v1/cities?userId=$testUserId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpectAll(
                MockMvcResultMatchers.jsonPath("\$[0].id").value(7),
                MockMvcResultMatchers.jsonPath("\$[1].id").value(8),
                MockMvcResultMatchers.jsonPath("\$[2].id").value(3),
                MockMvcResultMatchers.jsonPath("\$[3].id").value(2),
                MockMvcResultMatchers.jsonPath("\$[4].id").value(1)
            )
    }
}