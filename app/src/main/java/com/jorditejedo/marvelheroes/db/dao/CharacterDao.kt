package com.jorditejedo.marvelheroes.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jorditejedo.marvelheroes.db.entity.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun selectObservableCharacters(): LiveData<List<Character>>

    @Query("DELETE FROM character WHERE id NOT IN (:charactersIds)")
    fun deleteOthersCharacters(charactersIds: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>)

    @Query("SELECT * FROM character WHERE id = :id")
    fun selectCharacter(id: Int) : Character
}