package com.jorditejedo.marvelheroes.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jorditejedo.marvelheroes.db.entity.Storie

class StorieConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToStorie(data: String) : Storie {
        val storieType = object : TypeToken<Storie>(){}.type

        return gson.fromJson(data, storieType)
    }

    @TypeConverter
    fun storieToString(storie: Storie) : String {
        return gson.toJson(storie)
    }
}