package com.aslansari.pokedeck.pokemon.dto

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("back_default")
    val backDefaultUrl:String,
    @SerializedName("front_default")
    val frontDefaultUrl:String,
)
