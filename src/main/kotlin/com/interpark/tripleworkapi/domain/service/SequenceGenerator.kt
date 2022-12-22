package com.interpark.tripleworkapi.domain.service

interface SequenceGenerator {
    fun generate(name: String): Long
}