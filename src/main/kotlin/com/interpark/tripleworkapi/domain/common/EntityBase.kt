package com.interpark.tripleworkapi.domain.common

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
abstract class EntityBase {
    @Version
    protected val version: Long? = null

    @field:CreationTimestamp
    @Column(name = "createdAt")
    val createdAt: LocalDateTime? = LocalDateTime.now()

    @field:UpdateTimestamp
    @Column(name = "updatedAt")
    val updatedAt: LocalDateTime? = LocalDateTime.now()

}