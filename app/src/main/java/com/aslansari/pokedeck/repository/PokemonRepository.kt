package com.aslansari.pokedeck.repository

import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.pokemon.dto.PokemonDTO
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val client: PokemonService
) {

    suspend fun getPokemonList(limit: Int, offset: Int): List<PokemonDTO> {
        val pokemonListCache: MutableList<PokemonDTO> = mutableListOf()
        val response = client.getPokemonList(limit = limit, offset = offset)
        for (result in response.results) {
            pokemonListCache.add(client.getPokemon(result.url))
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