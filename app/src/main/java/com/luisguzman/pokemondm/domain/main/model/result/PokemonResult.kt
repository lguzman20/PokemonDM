package com.luisguzman.pokemondm.domain.main.model.result

import com.google.gson.annotations.SerializedName
import com.luisguzman.pokemondm.domain.main.model.response.PokemonStat
import com.luisguzman.pokemondm.domain.main.model.response.PokemonTypeItem
import com.luisguzman.pokemondm.domain.main.model.response.Sprite

data class PokemonResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprite,
    @SerializedName("types")
    val types: List<PokemonTypeItem>,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("stats")
    val stats: List<PokemonStat>,
    var image: String,
    var species: PokemonSpeciesResult
)