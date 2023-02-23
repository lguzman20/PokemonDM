package com.luisguzman.pokemondm.domain.main

import com.luisguzman.pokemondm.domain.main.model.response.*
import retrofit2.http.Path

interface HomeRepository {

    suspend fun getRegions() : List<Region>?

    suspend fun getDetailedRegion(region: String) : List<Pokedex>?

    suspend fun getPokedex(pokedex: String) : List<Pokemon>?

    suspend fun getPokeDetails(id: Int): PokeItemDetails?

}