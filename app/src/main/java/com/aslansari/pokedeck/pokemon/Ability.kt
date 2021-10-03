package com.aslansari.pokedeck.pokemon

import com.aslansari.pokedeck.pokemon.model.VerboseEffect
import com.google.gson.annotations.SerializedName

data class Ability(
    val id: Int,
    val name: String,
    @SerializedName("effect_entries")
    val effectEntries: VerboseEffect,
)