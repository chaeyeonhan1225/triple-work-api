package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import com.interpark.tripleworkapi.domain.user.User
import com.interpark.tripleworkapi.domain.user.UserId
import com.interpark.tripleworkapi.domain.user.UserParam
import com.interpark.tripleworkapi.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserApplication(
    private val sequenceGenerator: SequenceGenerator,
    private val repository: UserRepository
) {
    fun create(param: UserParam): User {
        val userId = UserId(sequenceGenerator.generate(User::class.java.simpleName))

        val user = User(id = userId, param = param)
        return repository.save(user)
    }
}