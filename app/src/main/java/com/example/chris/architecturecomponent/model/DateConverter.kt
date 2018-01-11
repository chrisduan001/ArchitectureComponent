package com.example.chris.architecturecomponent.model

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by Chris on 1/8/18.
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(millis: Long?) : Date? = if (millis == null) null else Date(millis)

    @TypeConverter
    fun fromDate(date: Date?) : Long? = date?.time
}