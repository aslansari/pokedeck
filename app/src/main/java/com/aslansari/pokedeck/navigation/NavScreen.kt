package com.aslansari.pokedeck.navigation

sealed class NavScreen(val route: String) {
    object PokemonList: NavScreen("pokemon_list")
}

sealed class NestedNavGraph(val route: String) {
}