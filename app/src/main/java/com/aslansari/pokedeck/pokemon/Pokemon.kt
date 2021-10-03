package com.aslansari.pokedeck.pokemon

import com.aslansari.pokedeck.pokemon.model.NamedAPIResource
import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val name: String,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val height: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    val weight: Int,
    val abilities: List<PokemonAbility>?,
    val sprites: Sprites?
) {
    constructor(name: String) : this(name = name, id = 0, baseExperience = 0, height = 0, isDefault = false, weight = 0, abilities = null, sprites = null)
}

data class Sprites(
    @SerializedName("back_default")
    val backDefaultUrl:String,
    @SerializedName("front_default")
    val frontDefaultUrl:String,
)

data class PokemonAbility(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int,
    val ability: NamedAPIResource
)