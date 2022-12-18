package com.aslansari.pokedeck.navigation

sealed class NavScreen(val route: String) {
    object PokemonList: NavScreen("pokemon_list")
    object PokemonDetail: NavScreen("pokemon_detail") {
        val routeWithArgs = route.plus("/{name}")
    }
}

sealed class NestedNavGraph(val route: String) {
}