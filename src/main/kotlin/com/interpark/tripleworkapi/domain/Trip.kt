package com.interpark.tripleworkapi.domain


import com.interpark.tripleworkapi.domain.param.TripParam
import java.util.*
import javax.persistence.*

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

}

