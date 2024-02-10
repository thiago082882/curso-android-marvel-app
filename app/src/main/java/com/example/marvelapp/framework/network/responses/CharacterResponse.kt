package com.example.marvelapp.framework.network.responses

data class CharacterResponse(
    val id:String,
    val name : String ,
    val thumbnail : ThumbnailResponse
)
