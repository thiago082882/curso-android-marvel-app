package com.example.marvelapp.framework

import androidx.paging.PagingSource
import com.example.marvelapp.framework.network.responses.DataWrapperResponse
import com.thiago.core.data.repository.CharacterRemoteDataSource
import com.thiago.core.data.repository.CharacterRepository
import com.thiago.core.domain.model.Character
import javax.inject.Inject

class CharacterRepositoryImpl @Inject  constructor(
    private val remoteDataSource: CharacterRemoteDataSource<DataWrapperResponse>
): CharacterRepository  {
    override fun getCharacters(query: String): PagingSource<Int, Character> {
        TODO("Not yet implemented")
    }
}