package com.interpark.tripleworkapi.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class NotFoundException (
    override val message: String
): ResponseStatusException(
    HttpStatus.BAD_REQUEST,
    message
)