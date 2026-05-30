package com.example.marvelapp.framework.di

import com.thiago.core.usecases.GetCharactersUseCase
import com.thiago.core.usecases.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindCharactersUseCase(useCaseImpl: GetCharactersUseCaseImpl):GetCharactersUseCase
}