package com.aslansari.pokedeck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aslansari.pokedeck.pokemon.Ability
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.pokemon.dto.PokemonDTO
import com.aslansari.pokedeck.repository.PokemonRepository
import com.aslansari.pokedeck.util.ProbabilitySelector
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val dispatcher: CoroutineDispatcher, private val pokemonRepository: PokemonRepository): ViewModel() {

    private val _pokemonFlow = MutableStateFlow(ResultModel(isLoading = false, pokemonList = mutableListOf(), isError =  false))
    val pokemonFlow: StateFlow<ResultModel> = _pokemonFlow

    fun getPokemonList() = viewModelScope.launch {
        _pokemonFlow.value = ResultModel(isLoading = true, mutableListOf(), isError = false)
        val pokemonList = mutableListOf<Pokemon>()
        pokemonRepository.getPokemonList().forEach {
            pokemonList.add(Pokemon(it.name, it.baseExperience, level = 0, frontDefaultUrl = it.sprites!!.frontDefaultUrl, abilities = listOf()))
        }
        _pokemonFlow.value = ResultModel(false, pokemonList = pokemonList, false)
    }
}

class PokemonViewModelFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(Dispatchers.IO, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}