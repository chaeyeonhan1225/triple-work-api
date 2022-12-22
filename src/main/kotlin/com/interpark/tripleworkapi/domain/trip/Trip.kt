package com.interpark.tripleworkapi.domain.trip


import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.param.TripParam
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Where(clause = "status > 0")
class Trip(
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id", nullable = false))
    val id: TripId,

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

    @OneToMany(mappedBy = "trip", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var citiesToTrip: List<TripCity> = param.cityIds.mapIndexed {
        index, it -> createTripCity(cityId = it, indexNo = index)
    }
        private set

    private fun createTripCity(cityId: Long, indexNo: Int): TripCity = TripCity(
        cityId = CityId(cityId),
        trip = this,
        indexNo = indexNo
    )

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

