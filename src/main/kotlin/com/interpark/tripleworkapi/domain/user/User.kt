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

    param: UserParam
): EntityBase() {
    @Column
    var email: String = param.email

    @Column
    var nickname: String = param.nickname

    @Column
    var status: CommonState = CommonState.ACTIVE
}