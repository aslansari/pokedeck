package com.aslansari.pokedeck.repository

import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.network.RetrofitClient

class PokemonRepository(private val client: PokemonService) {
    suspend fun getPokemonList() = client.getPokemonList()
    suspend fun getPokemon(url: String) = client.getPokemon(url = url)
}