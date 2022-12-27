package com.interpark.tripleworkapi.domain.trip


import com.fasterxml.jackson.annotation.JsonIgnore
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.common.EntityBase
import com.interpark.tripleworkapi.domain.user.UserId
import org.hibernate.annotations.BatchSize
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Where(clause = "status > 0")
@Table(
    indexes = [
        Index(name = "idx_Trip_userId", columnList = "userId"),
        Index(name = "idx_Trip_cityId", columnList = "cityId"),
        Index(name = "idx_Trip_startedAt", columnList = "startedAt"),
        Index(name = "idx_Trip_endedAt", columnList = "endedAt"),
    ]
)
class Trip(
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id", nullable = false))
    val id: TripId,

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "userId", nullable = false))
    val userId: UserId,

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "cityId", nullable = false))
    var cityId: CityId,

    param: TripParam
): EntityBase() {
    @Column(nullable = false, length = 128)
    var title: String = param.title
        private set

    @Embedded
    var plan: Plan = Plan(startedAt = param.plan.startedAt, endedAt = param.plan.endedAt)
        private set

    @Column(nullable = false, length = 24)
    var status: CommonState = CommonState.ACTIVE
        private set

    @JsonIgnore
    @OneToMany(mappedBy = "trip", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    private var tripTags: List<Tag> = param.tags.map {
        Tag(
            trip = this,
            content = it
        )
    }

    val tags: List<String>
        get() = tripTags.filter { it.status == CommonState.ACTIVE }.map { it.content }

    fun update(param: TripParam) {
        title = param.title
        plan = Plan(startedAt = param.plan.startedAt, endedAt = param.plan.endedAt)
        cityId = CityId(param.cityId)
        updateTags(param.tags)
    }

    private fun updateTags(newTags: List<String>) {
        val tagsToBeDeleted = tripTags.filter { tag ->
            !newTags.contains(tag.content)
        }
        val tagsToBeCreated = newTags.filter {
            !tags.contains(it)
        }

        tagsToBeDeleted.forEach { it.delete() }
        tripTags += tagsToBeCreated.map { Tag(trip = this, content = it) }

    }

    fun delete() {
        status = CommonState.DELETED
    }

}

