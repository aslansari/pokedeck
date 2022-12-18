package com.aslansari.pokedeck.pokemon.dto

import com.google.gson.annotations.SerializedName

data class StatDTO(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val detail: StatDetailDTO,
)

data class StatDetailDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
)
