package com.aslansari.pokedeck.pokemon

data class Pokemon(
    val name: String,
    val experience: Int = 0,
    val level: Int = 0,
    val abilities : List<Ability> = listOf(),
    val frontDefaultUrl: String = "",
)