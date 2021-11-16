package com.w4ereT1ckRtB1tch.moviefan.data.db

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


class Converters {

    @TypeConverter
    fun fromTimestampToLocalDate(timeStamp: Long?): LocalDate? {
        return timeStamp?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
        }
    }

    @TypeConverter
    fun fromLocalDateToTimestamp(localDate: LocalDate?): Long? {
        return localDate
            ?.atStartOfDay(ZoneId.systemDefault())
            ?.toInstant()
            ?.toEpochMilli()
    }
}