package com.luisguzman.pokemondm.domain.main.model.response

import com.google.gson.annotations.SerializedName

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: PokemonStatName
)
