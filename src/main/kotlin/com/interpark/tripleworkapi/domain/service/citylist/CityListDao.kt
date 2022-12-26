package com.interpark.tripleworkapi.domain.service.citylist

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class CityListDao(
    private val jdbcTemplate: JdbcTemplate
) {
    fun execute() {

    }

}