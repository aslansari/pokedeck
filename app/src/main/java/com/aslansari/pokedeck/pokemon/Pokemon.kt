package com.aslansari.pokedeck.pokemon

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name:String,
    val sprites: Sprites?
) {
    constructor(name: String) : this(name = name, sprites = null)
}

data class Sprites(
    @SerializedName("back_default")
    val backDefaultUrl:String,
    @SerializedName("front_default")
    val frontDefaultUrl:String,
)
