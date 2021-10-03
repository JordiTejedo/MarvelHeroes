package com.jorditejedo.marvelheroes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jorditejedo.marvelheroes.db.dao.CharacterDao
import com.jorditejedo.marvelheroes.db.entity.Character

@Database(entities = [Character::class], version = 5, exportSchema = false)
abstract class MarvelHeroesDatabase : RoomDatabase() {
    abstract val characterDao : CharacterDao
}