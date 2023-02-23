package com.luisguzman.pokemondm.domain.main.model.result

import com.google.gson.annotations.SerializedName
import com.luisguzman.pokemondm.domain.main.model.response.Pokedex

data class RegionDetailedResult (
    @SerializedName("pokedexes")
    val pokedexes: List<Pokedex>
)