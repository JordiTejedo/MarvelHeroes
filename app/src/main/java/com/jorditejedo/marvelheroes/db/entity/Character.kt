package com.jorditejedo.marvelheroes.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.jorditejedo.marvelheroes.db.typeconverter.*

@Entity
@TypeConverters(
    ListTypeConverter::class,
    ThumbnailConverter::class,
    ComicConverter::class,
    SerieConverter::class,
    StorieConverter::class,
    EventConverter::class
)
data class Character(
    @PrimaryKey val id : Int,
    val name : String,
    val description : String,
    val modified : String,
    val thumbnail : Thumbnail,
    val resourceURI : String,
    val comics : Comic,
    val series : Serie,
    val stories : Storie,
    val events : Event,
    val urls : List<Url>
)

data class Comic (
    val available : Int,
    val collectionURI : String,
    val items : List<Item>,
    val returned : Int
)

data class Serie (
    val available : Int,
    val collectionURI : String,
    val items : List<Item>,
    val returned : Int
)

class Event (
    val available : Int,
    val collectionURI : String,
    val items : List<Item>,
    val returned : Int
)

data class Storie (
    val available : Int,
    val collectionURI : String,
    val items : List<Item>,
    val returned : Int
)

data class Url (
    val type : String,
    val url : String
)

data class Thumbnail(
    val path : String,
    val extension : String
)

data class Item (
    val resourceURI : String,
    val name : String
)

enum class UrlType {
    detail,
    comiclink,
    wiki
}