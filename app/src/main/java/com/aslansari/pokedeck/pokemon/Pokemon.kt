package com.aslansari.pokedeck.pokemon

import androidx.compose.runtime.Immutable

@Immutable
data class Pokemon(
    val name: String,
    val experience: Int = 0,
    val level: Int = 0,
    val abilities : List<Ability> = listOf(),
    val frontDefaultUrl: String = "",
)