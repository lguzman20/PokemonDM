package com.luisguzman.pokemondm.domain.main.model.response

import com.google.gson.annotations.SerializedName

data class Region (
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)