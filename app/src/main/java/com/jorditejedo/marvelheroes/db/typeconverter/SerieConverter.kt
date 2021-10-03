package com.jorditejedo.marvelheroes.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jorditejedo.marvelheroes.db.entity.Serie

class SerieConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToSerie(data: String) : Serie {
        val serieType = object : TypeToken<Serie>(){}.type

        return gson.fromJson(data, serieType)
    }

    @TypeConverter
    fun serieToString(serie: Serie) : String {
        return gson.toJson(serie)
    }
}