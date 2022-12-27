package com.interpark.tripleworkapi.domain.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class EventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun <T : Any>publish(event: T) {
       applicationEventPublisher.publishEvent(event)
    }
}