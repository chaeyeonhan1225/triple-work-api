package com.interpark.tripleworkapi.domain.city

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.interpark.tripleworkapi.domain.trip.TripId
import java.io.Serializable
import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CityId @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @Access(AccessType.FIELD)
    @Column
    @get:JsonValue
    val value: Long
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CityId

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}