package com.interpark.tripleworkapi.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class CityUnableDeleteException (
    override val message: String
): ResponseStatusException(
    HttpStatus.FORBIDDEN,
    message
)