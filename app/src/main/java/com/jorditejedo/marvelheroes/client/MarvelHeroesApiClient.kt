package com.jorditejedo.marvelheroes.client

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelHeroesApiClient {
    @GET("characters")
    suspend fun getCharacters(@Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String) : Response<DTO.GetCharactersResponse>
}