package com.thiago.core.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thiago.core.data.repository.CharacterRepository
import com.thiago.core.domain.model.Character
import com.thiago.core.usecases.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val charactersRepository: CharacterRepository
) : PagingUseCase<GetCharacterUseCase.GetCharactersParams,Character>(){

    override fun createFlowObservable(params: GetCharactersParams): Flow<PagingData<Character>> {
      return Pager(config = params.pagingConfig){
          charactersRepository.getCharacters(params.query)
      }.flow

    }

    data class GetCharactersParams(val query : String,val pagingConfig: PagingConfig)


}