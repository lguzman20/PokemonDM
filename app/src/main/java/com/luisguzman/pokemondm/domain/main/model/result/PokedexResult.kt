package com.luisguzman.pokemondm.domain.main.model.result

import com.google.gson.annotations.SerializedName
import com.luisguzman.pokemondm.domain.main.model.response.Pokemon

data class PokedexResult(
    @SerializedName("pokemon_entries")
    val pokemonEntries: List<Pokemon>
)