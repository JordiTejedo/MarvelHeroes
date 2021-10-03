package com.jorditejedo.marvelheroes.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.jorditejedo.marvelheroes.db.entity.Url

class ListTypeConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToUrlsList(data: String?): List<Url> {
        if (data == null) {
            return ArrayList()
        }

        val listType = object : TypeToken<List<Url>>(){}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun urlsListToString(strList: List<Url>): String {
        return gson.toJson(strList)
    }
}