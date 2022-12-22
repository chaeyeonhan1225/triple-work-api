package com.interpark.tripleworkapi.infrastructure.sequence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "DatabaseSequence")
class DataBaseSequence(
    @Id
    @Column(length = 36)
    val id: String,

    val seq: Long = 1
) {
}