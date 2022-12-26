package com.interpark.tripleworkapi.domain.viewlog

import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.common.EntityBase
import com.interpark.tripleworkapi.domain.user.UserId
import javax.persistence.*

@Entity
class CityViewLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "userId", nullable = false))
    val userId: UserId,

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "cityId", nullable = false))
    val cityId: CityId
): EntityBase() {

}