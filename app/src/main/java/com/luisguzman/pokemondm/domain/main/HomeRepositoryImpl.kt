package com.luisguzman.pokemondm.domain.main

import com.luisguzman.pokemondm.data.network.api.HomeApiService
import com.luisguzman.pokemondm.domain.main.model.response.*
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService : HomeApiService
) : HomeRepository {

    override suspend fun getRegions(): List<Region>? {
        val response = apiService.getRegions()
        return response.body()?.results
    }

    override suspend fun getDetailedRegion(region: String): List<Pokedex>? {
        val response = apiService.getDetailedRegion(region)
        return response.body()?.pokedexes
    }

    override suspend fun getPokedex(pokedex: String): List<Pokemon>? {
        val response = apiService.getPokedex(pokedex)
        return response.body()?.pokemonEntries
    }

    override suspend fun getPokeDetails(id: Int): PokeItemDetails? {
        val response = apiService.getDetailsPokemon(id)
        return response.body()?.toDomain()
    }



}