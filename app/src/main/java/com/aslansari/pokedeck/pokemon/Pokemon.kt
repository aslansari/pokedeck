package com.aslansari.pokedeck.pokemon

import androidx.compose.runtime.Immutable

@Immutable
data class Pokemon(
    val name: String,
    val experience: Int = 0,
    val level: Int = 0,
    val abilities : List<Ability> = listOf(),
    val stats: List<PokemonStat> = listOf(),
    val frontDefaultUrl: String = "",
)

data class PokemonStat(
    val name: String,
    val stat: Int,
)