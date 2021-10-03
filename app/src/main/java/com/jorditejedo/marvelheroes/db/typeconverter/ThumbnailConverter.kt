package com.jorditejedo.marvelheroes.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jorditejedo.marvelheroes.db.entity.Thumbnail

class ThumbnailConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToThumbnail(data: String) : Thumbnail {
        val thumbnailType = object : TypeToken<Thumbnail>(){}.type

        return gson.fromJson(data, thumbnailType)
    }

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail) : String {
        return gson.toJson(thumbnail)
    }
}