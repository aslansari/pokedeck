package com.aslansari.pokedeck.feature.details

import androidx.lifecycle.SavedStateHandle
import com.aslansari.pokedeck.arch.ui.BaseViewModel
import com.aslansari.pokedeck.arch.ui.UIEvent
import com.aslansari.pokedeck.arch.ui.UIState
import com.aslansari.pokedeck.pokemon.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
) : BaseViewModel<PokemonDetailUIState, PokemonDetailUIEvent>() {
    override fun createInitialState(): PokemonDetailUIState = PokemonDetailUIState()

    init {
        val name = stateHandle.get<String>("name")
        setState { currentState.copy(name = name.orEmpty().ifEmpty { "empty" }) }
    }
}

data class PokemonDetailUIState(
    val loading: Boolean = false,
    val name: String = "",
    val pokemon: Pokemon? = null,
) : UIState

sealed interface PokemonDetailUIEvent : UIEvent