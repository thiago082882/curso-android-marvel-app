package com.thiago.core.data.network

import com.thiago.core.data.network.responses.DataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun  getCharacters(
        @QueryMap
        queries : Map<String,String>
    ):DataWrapperResponse
}