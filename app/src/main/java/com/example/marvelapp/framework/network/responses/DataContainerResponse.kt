package com.example.marvelapp.framework.network.responses

data class DataContainerResponse(
    val offset:Int,
    val total :Int,
    val results : List<CharacterResponse>
)
