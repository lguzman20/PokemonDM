package com.luisguzman.pokemondm.domain.main.model.response

import com.google.gson.annotations.SerializedName

data class PokemonStatName(
    @SerializedName("name")
    val name: String
)
