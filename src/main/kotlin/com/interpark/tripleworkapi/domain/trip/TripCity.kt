package com.interpark.tripleworkapi.domain.trip

import com.interpark.tripleworkapi.domain.common.CommonState
import java.io.Serializable
import javax.persistence.*

/**
 * Trip - City 매핑 테이블
 */
@Entity
class TripCity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

//    @Column(nullable = false)
//    val tripId: Long = 0,

    @Column(nullable = false)
    val cityId: Long = 0,


    @Column(nullable = false)
    val indexNo: Int = 0
) {
    @Column
    var status: CommonState = CommonState.ACTIVE
}
