package com.interpark.tripleworkapi.domain.city

import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.common.EntityBase
import com.interpark.tripleworkapi.domain.param.CityParam
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Where(clause = "status > 0")
@Table(
    indexes = [
        Index(name = "idx_City_createdAt", columnList = "createdAt"),
        Index(name = "idx_City_updatedAt", columnList = "updatedAt")
    ]
)
class City(
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id", nullable = false))
    val id: CityId,

    param: CityParam
): EntityBase() {
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
