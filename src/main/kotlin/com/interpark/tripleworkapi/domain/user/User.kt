package com.interpark.tripleworkapi.domain.user

import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.common.EntityBase
import javax.persistence.*

@Entity
@Table(name = "`User`")
class User(
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id", nullable = false))
    val id: UserId,
): EntityBase() {
    @Column
    var email: String = ""

    @Column
    var nickname: String = ""

    @Column
    var status: CommonState = CommonState.ACTIVE
}