package com.example.marvelapp.framework.network

import com.example.marvelapp.framework.network.responses.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun  getCharacters(
        @QueryMap
        queries : Map<String,String>
    ): DataWrapperResponse
}