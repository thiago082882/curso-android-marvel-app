package com.example.marvelapp.framework.remote

import com.example.marvelapp.framework.network.MarvelApi
import com.example.marvelapp.framework.network.responses.DataWrapperResponse
import com.thiago.core.data.repository.CharacterRemoteDataSource
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private  val marvelApi : MarvelApi
) : CharacterRemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
       return marvelApi.getCharacters(queries = queries)
    }
}