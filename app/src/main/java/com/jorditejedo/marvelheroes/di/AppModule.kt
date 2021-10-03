package com.jorditejedo.marvelheroes.di

import com.bandalux.lynx.client.adapter.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import com.jorditejedo.marvelheroes.BuildConfig
import com.jorditejedo.marvelheroes.client.MarvelHeroesApiClient
import com.jorditejedo.marvelheroes.utils.MarvelHeroesExecutors
import com.jorditejedo.marvelheroes.utils.NullOnEmptyConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideMarvelApiClient(): MarvelHeroesApiClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.readTimeout(30, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .client(okHttpClient.build())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .baseUrl(BuildConfig.MARVEL_API_URL)
            .build()
            .create(MarvelHeroesApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideExecutors(): MarvelHeroesExecutors {
        return MarvelHeroesExecutors()
    }
}