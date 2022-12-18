package com.aslansari.pokedeck.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.aslansari.pokedeck.arch.ui.BaseViewModel
import com.aslansari.pokedeck.arch.ui.UIEvent
import com.aslansari.pokedeck.arch.ui.UIState
import com.aslansari.pokedeck.di.Dispatcher
import com.aslansari.pokedeck.di.PokeDispatchers
import com.aslansari.pokedeck.feature.pokemon.GetPagedPokemonListUseCase
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    @Dispatcher(PokeDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val pokemonRepository: PokemonRepository,
    private val getPagedPokemonListUseCase: GetPagedPokemonListUseCase,
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
        viewModelScope.launch {
            val pagedFlow = getPagedPokemonListUseCase()
            setState {
                currentState.copy(pokemonFlow = pagedFlow)
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
    val pokemonList: List<Pokemon> = listOf(),
    val pokemonFlow: Flow<PagingData<Pokemon>>? = null,
) : UIState

sealed interface PokemonListUIEvent : UIEvent {
    data class NavigateToDetails(val pokemonId: String) : PokemonListUIEvent
}