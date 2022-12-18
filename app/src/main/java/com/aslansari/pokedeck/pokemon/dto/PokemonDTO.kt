package com.aslansari.pokedeck.pokemon.dto

import com.aslansari.pokedeck.pokemon.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("sprites")
    val sprites: Sprites?
)

fun PokemonDTO.toModel(): Pokemon {
    return Pokemon(
        this.name,
        this.baseExperience,
        level = 0,
        frontDefaultUrl = this.sprites!!.frontDefaultUrl,
        abilities = listOf()
    )
}