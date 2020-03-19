package com.valuekart.bloodbank.data.model.entity

import android.arch.persistence.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun fromLong(value: Long?): BigDecimal? {
        return if (value == null) null else BigDecimal(value).divide(BigDecimal(100))
    }

    @TypeConverter
    fun toLong(bigDecimal: BigDecimal?): Long? {
        return bigDecimal?.multiply(BigDecimal(100))?.toLong()

    }

}