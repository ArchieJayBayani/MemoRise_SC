package com.example.memorise.feature_note.data

import androidx.room.TypeConverter
import com.example.memorise.feature_note.domain.model.FormattedSegment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromFormattedSegmentList(value: List<FormattedSegment>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toFormattedSegmentList(value: String): List<FormattedSegment> {
        val gson = Gson()
        val listType = object : TypeToken<List<FormattedSegment>>() {}.type
        return gson.fromJson(value, listType)
    }
}