package com.interpark.tripleworkapi.domain.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object

@Component
class EventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun <T : Any>publish(event: T) {
       applicationEventPublisher.publishEvent(event)
    }
}