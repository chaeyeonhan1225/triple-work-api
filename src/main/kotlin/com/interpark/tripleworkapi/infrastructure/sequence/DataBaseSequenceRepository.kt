package com.interpark.tripleworkapi.infrastructure.sequence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DataBaseSequenceRepository: JpaRepository<DataBaseSequence, String> {
    @Modifying
    @Query("Update DataBaseSequence set seq = seq + :count Where id = :id")
    fun updateSeq(@Param("id") id: String, @Param("count") count: Long)
}