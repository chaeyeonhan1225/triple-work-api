package com.interpark.tripleworkapi.controller

import com.interpark.tripleworkapi.application.UserApplication
import com.interpark.tripleworkapi.domain.user.User
import com.interpark.tripleworkapi.domain.user.UserParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val service: UserApplication
) {
    @PostMapping
    fun create(@RequestBody param: UserParam): User {
        return service.create(param)
    }
}