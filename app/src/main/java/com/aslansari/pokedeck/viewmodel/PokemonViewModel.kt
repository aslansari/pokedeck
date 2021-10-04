package com.aslansari.pokedeck.viewmodel

import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.repository.PokemonRepository

class PokemonViewModel(private val pokemonRepository: PokemonRepository) {
    suspend fun getPokemonList(): List<Pokemon> = pokemonRepository.getPokemonList()
}