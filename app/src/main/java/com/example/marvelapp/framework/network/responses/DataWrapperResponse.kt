package com.example.marvelapp.framework.network.responses

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: DataContainerResponse
)