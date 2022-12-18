package com.aslansari.pokedeck.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import com.aslansari.pokedeck.arch.domain.Resource
import com.aslansari.pokedeck.arch.domain.asResource
import com.aslansari.pokedeck.arch.ui.BaseViewModel
import com.aslansari.pokedeck.arch.ui.UIEvent
import com.aslansari.pokedeck.arch.ui.UIState
import com.aslansari.pokedeck.di.Dispatcher
import com.aslansari.pokedeck.di.PokeDispatchers
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    @Dispatcher(PokeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val pokemonRepository: PokemonRepository
) : BaseViewModel<PokemonListUIState, PokemonListUIEvent>() {

    override fun createInitialState(): PokemonListUIState = PokemonListUIState()

    private val _pokemonFlow = MutableStateFlow(
        ResultModel(
            isLoading = false,
            pokemonList = mutableListOf(),
            isError = false
        )
    )
    val pokemonFlow: StateFlow<ResultModel> = _pokemonFlow

    init {
        getPokemonList()
    }

    fun getPokemonList() = viewModelScope.launch {
        flow {
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
            emit(pokemonList)
        }.asResource()
            .collect { state ->
                when (state) {
                    Resource.Loading -> setState { currentState.copy(loading = true) }
                    is Resource.Error -> setState { currentState.copy(loading = false) }
                    is Resource.Success -> setState {
                        currentState.copy(loading = false, pokemonList = state.data)
                    }
                }

            }
    }

    fun onPokemonClick(pokemonName: String) {
        setEvent(PokemonListUIEvent.NavigateToDetails(pokemonName))
    }
}

@Stable
data class PokemonListUIState(
    val loading: Boolean = false,
    val pokemonList: List<Pokemon> = listOf()
) : UIState

sealed interface PokemonListUIEvent : UIEvent {
    data class NavigateToDetails(val pokemonId: String) : PokemonListUIEvent
}