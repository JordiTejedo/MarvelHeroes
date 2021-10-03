package com.jorditejedo.marvelheroes.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jorditejedo.marvelheroes.db.entity.Comic

class ComicConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToComic(data: String) : Comic {
        val comicType = object : TypeToken<Comic>(){}.type

        return gson.fromJson(data, comicType)
    }

    @TypeConverter
    fun comicToString(comic: Comic) : String {
        return gson.toJson(comic)
    }
}