package com.aslansari.pokedeck.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslansari.pokedeck.di.Dispatcher
import com.aslansari.pokedeck.di.PokeDispatchers
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    @Dispatcher(PokeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonFlow = MutableStateFlow(
        ResultModel(
            isLoading = false,
            pokemonList = mutableListOf(),
            isError = false
        )
    )
    val pokemonFlow: StateFlow<ResultModel> = _pokemonFlow

    fun getPokemonList() = viewModelScope.launch {
        _pokemonFlow.value = ResultModel(isLoading = true, mutableListOf(), isError = false)
        val pokemonList = mutableListOf<Pokemon>()
        pokemonRepository.getPokemonList().forEach {
            pokemonList.add(
                Pokemon(
                    it.name,
                    it.baseExperience,
                    level = 0,
                    frontDefaultUrl = it.sprites!!.frontDefaultUrl,
                    abilities = listOf()
                )
            )
        }
        _pokemonFlow.value = ResultModel(false, pokemonList = pokemonList, false)
    }
}
