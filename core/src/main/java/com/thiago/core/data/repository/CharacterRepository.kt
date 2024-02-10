package com.thiago.core.data.repository

import androidx.paging.PagingSource
import com.thiago.core.domain.model.Character

interface CharacterRepository {

    fun getCharacters(query:String): PagingSource<Int,Character>
}