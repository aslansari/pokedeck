package com.aslansari.pokedeck.repository

import com.aslansari.pokedeck.network.PagedResponse
import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.pokemon.dto.PokemonDTO

class PokemonRepository(private val client: PokemonService) {
    private val pokemonListCache: MutableList<PokemonDTO> = mutableListOf()

    suspend fun getPokemonList(): List<PokemonDTO> {
        if (pokemonListCache.isEmpty()) {
            val response:PagedResponse = client.getPokemonList()
            for (result in response.results) {
                pokemonListCache.add(client.getPokemon(result.url))
            }
        }
        return pokemonListCache
    }

    suspend fun getPokemon(url: String): PokemonDTO {
        return client.getPokemon(url = url)
    }

    suspend fun getPokemon(id: Int) = client.getPokemon(id)

    suspend fun getAbility(id: Int) = client.getAbility(id)

    suspend fun getAbility(name: String) = client.getAbilityByName(name)

}