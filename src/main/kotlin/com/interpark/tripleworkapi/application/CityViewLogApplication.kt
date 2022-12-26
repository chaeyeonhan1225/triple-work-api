package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.user.UserId
import com.interpark.tripleworkapi.domain.viewlog.CityViewLog
import com.interpark.tripleworkapi.domain.viewlog.CityViewLogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CityViewLogApplication(
    private val repository: CityViewLogRepository
) {
    fun create(userId: UserId, cityId: CityId): CityViewLog {
        val cityViewLog = CityViewLog(
            userId = userId,
            cityId = cityId
        )

        return repository.save(cityViewLog)
    }
}