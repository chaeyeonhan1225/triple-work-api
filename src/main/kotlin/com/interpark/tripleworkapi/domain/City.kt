package com.interpark.tripleworkapi.domain

import com.interpark.tripleworkapi.domain.param.CityParam
import javax.persistence.*

@Entity
class City(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    param: CityParam
) {
    @Column(nullable = false, length = 128)
    var name: String = param.name

    @Column(nullable = false, length = 24)
    var status: CommonState = CommonState.ACTIVE

    @Column(nullable = true, length = 16)
    var countryCode: String? = param.countryCode

    fun update(param: CityParam) {
        name = param.name
        countryCode = param.countryCode
    }

    fun delete() {
        status = CommonState.DELETED
    }
}
