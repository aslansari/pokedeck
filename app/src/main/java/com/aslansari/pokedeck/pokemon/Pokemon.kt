package com.aslansari.pokedeck.pokemon

data class Pokemon(
    val name: String,
    val experience: Int,
    val level: Int,
    val abilities : List<Ability>,
    val frontDefaultUrl: String
) {
    constructor(name: String) : this(name = name, experience = 0, level = 0, abilities = listOf(), frontDefaultUrl = "")
}