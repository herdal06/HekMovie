package com.herdal.moviehouse.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntegerListTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun integerListToString(list: List<Int>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToIntegerList(data: String): List<Int> {
        val listType = object : TypeToken<List<Int>>(){}.type
        return gson.fromJson(data, listType)
    }
}