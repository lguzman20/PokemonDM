package com.luisguzman.pokemondm.domain.main.model.response

import com.google.gson.annotations.SerializedName

data class PokemonTypeItem(
    @SerializedName("slot")
    val slot: Integer,
    @SerializedName("type")
    val type: PokemonType
)
