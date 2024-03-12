package com.example.marvelapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapp.framework.network.responses.DataWrapperResponse
import com.example.marvelapp.framework.network.responses.toCharacterModel
import com.thiago.core.data.repository.CharacterRemoteDataSource
import com.thiago.core.domain.model.Character
import java.lang.Exception

class CharactersPagingSource(
    private val remoteDataSource: CharacterRemoteDataSource<DataWrapperResponse>,
    private val query: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {

            val offset = params.key ?: 0
            val queries = hashMapOf(
                "offset" to offset.toString()

            )
            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            val response = remoteDataSource.fetchCharacters(queries)
            val responseOffset = response.data.offset
            val totalCharacters  = response.data.total

            LoadResult.Page(
                data = response.data.results.map { it.toCharacterModel() },
                prevKey = null,
                nextKey = if(responseOffset < totalCharacters){
                    responseOffset + LIMIT
                }else null

            )

        } catch (e: Exception) {

            LoadResult.Error(e)

        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return  state.anchorPosition?.let { anchorPosition->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val  LIMIT = 20
    }

}