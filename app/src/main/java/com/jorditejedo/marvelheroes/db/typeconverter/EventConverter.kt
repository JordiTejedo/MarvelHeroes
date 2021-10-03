package com.jorditejedo.marvelheroes.db.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jorditejedo.marvelheroes.db.entity.Event

class EventConverter {

    private var gson = Gson()

    @TypeConverter
    fun stringToEvent(data: String) : Event {
        val eventType = object : TypeToken<Event>(){}.type

        return gson.fromJson(data, eventType)
    }

    @TypeConverter
    fun eventToString(event: Event) : String {
        return gson.toJson(event)
    }
}