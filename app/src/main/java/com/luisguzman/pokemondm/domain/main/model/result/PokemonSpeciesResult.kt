package com.luisguzman.pokemondm.domain.main.model.result

import com.google.gson.annotations.SerializedName
import com.luisguzman.pokemondm.domain.main.model.response.PokemonFlavorTextEntry

data class PokemonSpeciesResult(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<PokemonFlavorTextEntry>
)
