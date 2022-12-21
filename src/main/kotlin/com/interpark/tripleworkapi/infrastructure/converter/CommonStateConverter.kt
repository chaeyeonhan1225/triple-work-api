package com.interpark.tripleworkapi.infrastructure.converter

import com.interpark.tripleworkapi.domain.common.CommonState
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class CommonStateConverter: AttributeConverter<CommonState, Int> {
    override fun convertToDatabaseColumn(attribute: CommonState?): Int = attribute!!.value

    override fun convertToEntityAttribute(dbData: Int?): CommonState = CommonState.parse(dbData!!)
}