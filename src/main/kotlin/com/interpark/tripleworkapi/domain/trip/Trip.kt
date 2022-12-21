package com.interpark.tripleworkapi.domain.trip


import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.param.TripParam
import javax.persistence.*
import kotlin.collections.Set

@Entity
class Trip(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val userId: Long = 0,

    param: TripParam
) {
    @Column
    var title: String = param.title
        private set

    @Embedded
    var plan: Plan = Plan(startedAt = param.plan.startedAt, endedAt = param.plan.endedAt)
        private set

    @Column
    var status: CommonState = CommonState.ACTIVE
        private set

    @ElementCollection
    @CollectionTable(name = "trip_city", joinColumns = [JoinColumn(name = "trip_id")])
    var cityIds: Set<Long> = param.cityIds.map { it.toLong() }.toSet()

    val representativeCityId
        get() = cityIds.first()

    fun update(param: TripParam) {
        title = param.title
        plan = Plan(startedAt = param.plan.startedAt, endedAt = param.plan.endedAt)
        cityIds = param.cityIds.map { it.toLong() }.toSet()
    }

}

