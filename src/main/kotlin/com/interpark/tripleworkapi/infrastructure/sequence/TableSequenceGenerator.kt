package com.interpark.tripleworkapi.infrastructure.sequence

import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import org.springframework.stereotype.Service

@Service
class TableSequenceGenerator(private val repository: DataBaseSequenceRepository): SequenceGenerator {
    override fun generate(name: String): Long {
        if (repository.existsById(name)) {
            repository.updateSeq(name, 1)
        } else {
            repository.save(DataBaseSequence(name, 1))
        }
        return repository.findById(name).orElseThrow { RuntimeException("Fail to generate id") }.seq
    }

}