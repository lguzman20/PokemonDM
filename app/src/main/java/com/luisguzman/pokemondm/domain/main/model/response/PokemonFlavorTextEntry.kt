package com.luisguzman.pokemondm.domain.main.model.response

import com.google.gson.annotations.SerializedName

data class PokemonFlavorTextEntry(
    @SerializedName("flavor_text")
    val flavor_text: String
)
