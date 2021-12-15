package com.aslansari.pokedeck.pokemon.dto

import com.aslansari.pokedeck.pokemon.dto.Sprites
import com.aslansari.pokedeck.pokemon.model.NamedAPIResource
import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    val id: Int,
    val name: String,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val height: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    val weight: Int,
    val sprites: Sprites?
) {
    constructor(name: String) : this(name = name, id = 0, baseExperience = 0, height = 0, isDefault = false, weight = 0, sprites = null)
}