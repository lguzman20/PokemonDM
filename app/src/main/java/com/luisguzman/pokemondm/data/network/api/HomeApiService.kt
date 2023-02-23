package com.luisguzman.pokemondm.data.network.api

import com.luisguzman.pokemondm.data.network.model.PokeModelDetails
import com.luisguzman.pokemondm.data.network.model.ResultApi
import com.luisguzman.pokemondm.domain.main.model.result.PokedexResult
import com.luisguzman.pokemondm.domain.main.model.result.RegionDetailedResult
import com.luisguzman.pokemondm.domain.main.model.result.RegionResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApiService {

    @GET("region")
    suspend fun getRegions(): Response<RegionResult>

    @GET("region/{region}")
    suspend fun getDetailedRegion(@Path("region") region: String): Response<RegionDetailedResult>

    @GET("pokedex/{pokedex}")
    suspend fun getPokedex(@Path("pokedex") pokedex: String): Response<PokedexResult>

    @GET(value = "pokemon/{id}")
    suspend fun getDetailsPokemon(@Path("id") id: Int): Response<PokeModelDetails>

}