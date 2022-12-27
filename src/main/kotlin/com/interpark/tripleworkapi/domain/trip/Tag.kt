package com.interpark.tripleworkapi.domain.trip

import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.common.EntityBase
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Where(clause = "status > 0")
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "tripId",
        nullable = false,
        foreignKey = ForeignKey(name = "fk_Tag_tripId")
    )
    val trip: Trip,

    @Column(nullable = false, length = 128)
    val content: String = ""
): EntityBase() {

    @Column(nullable = false, length = 24)
    var status: CommonState = CommonState.ACTIVE
        private set

    fun delete() {
        status = CommonState.DELETED
    }
}