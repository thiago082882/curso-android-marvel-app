package com.thiago.core.usecases

import androidx.paging.PagingConfig
import androidx.paging.PagingSourceFactory
import com.nhaarman.mockitokotlin2.whenever
import com.thiago.core.data.repository.CharacterRepository
import com.thiago.testing.MainCoroutineRule
import com.thiago.testing.model.CharacterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: CharacterRepository

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private  val hero = CharacterFactory().create(CharacterFactory.Hero.ThreeDMan)
    private  val fakePagingSource = com.thiago.testing.pagingsource.PagingSourceFactory().create(
        listOf(hero)
    )

    @Before
    fun seUp() {
        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldValidateFlowPagingDataCreationWhenInvokeFromUseCaseIsCalled() =
        runBlockingTest {
            whenever(repository.getCharacters(""))
                .thenReturn(fakePagingSource)
            val result = getCharactersUseCase
                .invoke(GetCharactersUseCase.GetCharactersParams("", PagingConfig(20)))
        }


}