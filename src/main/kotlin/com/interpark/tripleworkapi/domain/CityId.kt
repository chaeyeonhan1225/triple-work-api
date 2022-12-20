package com.interpark.tripleworkapi.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class CityId(
    @Column(name = "id")
    val value: Long
): Serializable