package com.jorditejedo.marvelheroes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.jorditejedo.marvelheroes.BuildConfig
import com.jorditejedo.marvelheroes.client.MarvelHeroesApiClient
import com.jorditejedo.marvelheroes.client.type.ResponseType
import com.jorditejedo.marvelheroes.db.dao.CharacterDao
import com.jorditejedo.marvelheroes.db.entity.Character
import com.jorditejedo.marvelheroes.utils.Helpers
import com.jorditejedo.marvelheroes.utils.MarvelHeroesExecutors
import com.jorditejedo.marvelheroes.utils.Resource
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(private val marvelHeroesApiClient: MarvelHeroesApiClient, private val characterDao: CharacterDao, private val appExecutors: MarvelHeroesExecutors) {
    fun getAllCharactersFromServerAndStoreInLocalDB() = liveData {
        val disposable = emitSource(
            characterDao.selectObservableCharacters().map {
                Resource.loading(it)
            }
        )

        val timestamp = Calendar.getInstance().timeInMillis

        val getCharactersResponse = marvelHeroesApiClient.getCharacters(
            timestamp.toString(),
            BuildConfig.MARVEL_API_PUBLIC_KEY,
            Helpers.getHashForMarvelApi(timestamp)
        )

        try {
            if (getCharactersResponse.isSuccessful) {
                val data = getCharactersResponse.body()!!.data

                // Stop the previous emission to avoid dispatching the updated user
                // as `loading`.
                disposable.dispose()
                // Update the database.
                appExecutors.diskIO().execute {
                    characterDao.deleteOthersCharacters(data.results.map { it.id })
                    characterDao.insertCharacters(data.results)
                }
                // Re-establish the emission with success type.
                emitSource(
                    characterDao.selectObservableCharacters().map {
                        Resource.success(it)
                    }
                )
            }
        } catch (e: Exception) {
            // Any call to `emit` disposes the previous one automatically so we don't
            // need to dispose it here as we didn't get an updated value.
            emitSource(
                characterDao.selectObservableCharacters().map {
                    Resource.error(ResponseType.classify(e), it)
                }
            )
        }
    }

    fun getObservableCharactersFromLocalDB() : LiveData<List<Character>> {
        return characterDao.selectObservableCharacters()
    }

    fun getCharacterFromLocalDB(id: Int) : Character {
        lateinit var character: Character

        appExecutors.diskIO().submit{ character = characterDao.selectCharacter(id) }.get()

        return character
    }
}