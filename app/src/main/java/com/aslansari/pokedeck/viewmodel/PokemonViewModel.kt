package com.aslansari.pokedeck.viewmodel

import com.aslansari.pokedeck.repository.PokemonRepository

class PokemonViewModel(private val pokemonRepository: PokemonRepository) {
    suspend fun getPokemonList() = pokemonRepository.getPokemonList()
    suspend fun getPokemon(url: String) = pokemonRepository.getPokemon(url)
}