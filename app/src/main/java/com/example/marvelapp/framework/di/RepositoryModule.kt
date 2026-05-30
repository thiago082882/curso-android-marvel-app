package com.example.marvelapp.framework.di


import com.example.marvelapp.framework.CharactersRepositoryImpl
import com.example.marvelapp.framework.network.responses.DataWrapperResponse
import com.example.marvelapp.framework.remote.RetrofitCharactersDataSource
import com.thiago.core.data.repository.CharactersRemoteDataSource
import com.thiago.core.data.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindCharacterRepository(repository: CharactersRepositoryImpl) : CharacterRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RetrofitCharactersDataSource): CharactersRemoteDataSource<DataWrapperResponse>
}