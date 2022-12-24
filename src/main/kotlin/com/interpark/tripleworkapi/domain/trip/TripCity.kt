//package com.interpark.tripleworkapi.domain.trip
//
//import com.fasterxml.jackson.annotation.JsonIgnore
//import com.interpark.tripleworkapi.domain.city.CityId
//import com.interpark.tripleworkapi.domain.common.CommonState
//import org.hibernate.annotations.Where
//import java.io.Serializable
//import javax.persistence.*
//
///**
// * Trip - City 매핑 테이블
// */
//@Entity
//@Where(clause = "status > 0")
//class TripCity(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Long = 0,
//
//    @Embedded
//    @AttributeOverride(name = "value", column = Column(name = "cityId", nullable = false))
//    val cityId: CityId,
//
//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//        name = "tripId",
//        nullable = false,
//        foreignKey = ForeignKey(name = "fk_TripCity_tripId")
//    )
//    val trip: Trip,
//
//    @Column(nullable = false)
//    val indexNo: Int = 0
//) {
//    @Column
//    var status: CommonState = CommonState.ACTIVE
//
//    fun delete() {
//        status = CommonState.DELETED
//    }
//}
