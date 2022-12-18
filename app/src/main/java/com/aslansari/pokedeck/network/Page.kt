package com.aslansari.pokedeck.network

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<PokemonSummaryDTO>
)

data class PokemonSummaryDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)