package com.jorditejedo.marvelheroes.di

import android.app.Application
import androidx.room.Room
import com.jorditejedo.marvelheroes.db.MarvelHeroesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application) = Room.databaseBuilder(app, MarvelHeroesDatabase::class.java, "MarvelHeroesDB").fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideCharacterDao(db: MarvelHeroesDatabase) = db.characterDao
}