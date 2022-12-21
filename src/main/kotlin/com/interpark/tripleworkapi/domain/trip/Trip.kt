package com.interpark.tripleworkapi.domain.trip


import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.param.TripParam
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Where(clause = "status > 0")
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

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "tripId")
    var citiesToTrip: List<TripCity> = createCities(param.cityIds)
        private set

    private fun createCities(cityIds: List<Long>): List<TripCity> {
        return cityIds.mapIndexed { index, it -> TripCity(cityId = it, indexNo = index) }
    }

    fun update(param: TripParam) {
        title = param.title
        plan = Plan(startedAt = param.plan.startedAt, endedAt = param.plan.endedAt)
        // cityIds = param.cityIds.map { it.toLong() }.toSet()
    }

    fun delete() {
        status = CommonState.DELETED
        citiesToTrip.forEach { it.delete() }
    }

}

