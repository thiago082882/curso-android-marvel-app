package com.thiago.core.usecases.base

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutinesDispatchers(
    val io : CoroutineDispatcher,
    val computation : CoroutineDispatcher,
    val main : CoroutineDispatcher,
)
