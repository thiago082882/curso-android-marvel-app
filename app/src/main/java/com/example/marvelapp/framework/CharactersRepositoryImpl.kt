package com.example.marvelapp.framework

import androidx.paging.PagingSource
import com.example.marvelapp.framework.network.responses.DataWrapperResponse
import com.example.marvelapp.framework.paging.CharactersPagingSource
import com.thiago.core.data.repository.CharactersRemoteDataSource
import com.thiago.core.data.repository.CharacterRepository

import com.thiago.core.domain.model.Character
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource<DataWrapperResponse>
) : CharacterRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }
}