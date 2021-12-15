package com.aslansari.pokedeck.viewmodel

import com.aslansari.pokedeck.pokemon.Pokemon

data class ResultModel(val isLoading: Boolean, val pokemonList: MutableList<Pokemon>, val isError: Boolean)