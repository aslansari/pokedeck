package com.aslansari.pokedeck.repository

import com.aslansari.pokedeck.network.PagedResponse
import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.pokemon.Pokemon

class PokemonRepository(private val client: PokemonService) {
    private val pokemonListCache: MutableList<Pokemon> = mutableListOf()

    suspend fun getPokemonList(): List<Pokemon> {
        if (pokemonListCache.isEmpty()) {
            val response:PagedResponse = client.getPokemonList()
            for (result in response.results) {
                pokemonListCache.add(client.getPokemon(result.url))
            }
        }
        return pokemonListCache
    }

    suspend fun getPokemon(url: String) = client.getPokemon(url = url)
}