package com.thiago.core.data.repository

interface CharacterRemoteDataSource<T> {

    suspend fun fetchCaracters(queries : Map<String,String>): T
}