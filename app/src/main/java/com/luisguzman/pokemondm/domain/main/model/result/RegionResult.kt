package com.luisguzman.pokemondm.domain.main.model.result

import com.google.gson.annotations.SerializedName
import com.luisguzman.pokemondm.domain.main.model.response.Region

data class RegionResult (
    @SerializedName("count")
    var count: Int,
    @SerializedName("next")
    var next: String,
    @SerializedName("previous")
    var previous: String,
    @SerializedName("results")
    val results: List<Region>
)