package com.luisguzman.pokemondm.domain.main.model.response

import com.google.gson.annotations.SerializedName
import com.luisguzman.pokemondm.domain.main.model.result.PokemonResult

data class Pokemon(
    @SerializedName("entry_number")
    val entryNumber: Int,
    @SerializedName("pokemon_species")
    val pokemonSpecies: PokemonSpecies,
    var pokemonDetails: PokemonResult,
    var pokemonImage: String
)
