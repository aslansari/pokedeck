package com.aslansari.pokedeck.feature.details

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aslansari.pokedeck.arch.domain.Resource
import com.aslansari.pokedeck.arch.domain.asResource
import com.aslansari.pokedeck.arch.ui.BaseViewModel
import com.aslansari.pokedeck.arch.ui.UIEvent
import com.aslansari.pokedeck.arch.ui.UIState
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.pokemon.dto.PokemonDTO
import com.aslansari.pokedeck.pokemon.dto.toModel
import com.aslansari.pokedeck.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetPokemonByNameUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(name: String): Flow<Resource<Pokemon>> {
        return flow {
            val pokemon = pokemonRepository.getPokemonByName(name)
            emit(pokemon)
        }.map(PokemonDTO::toModel).asResource()
    }
}

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    stateHandle: SavedStateHandle,
    private val getPokemonByNameUseCase: GetPokemonByNameUseCase,
) : BaseViewModel<PokemonDetailUIState, PokemonDetailUIEvent>() {
    override fun createInitialState(): PokemonDetailUIState = PokemonDetailUIState()

    init {
        val name = stateHandle.get<String>("name")
        setState { currentState.copy(name = name.orEmpty().ifEmpty { "empty" }) }

        viewModelScope.launch {
            getPokemonByNameUseCase(currentState.name)
                .collect { res ->
                    when (res) {
                        Resource.Loading -> setState { currentState.copy(loading = true) }
                        is Resource.Error -> setState { currentState.copy(loading = false) }
                        is Resource.Success -> {
                            setState {
                                currentState.copy(
                                    loading = false,
                                    pokemon = res.data
                                )
                            }
                        }
                    }
                }
        }
    }
}

@Stable
data class PokemonDetailUIState(
    val loading: Boolean = false,
    val name: String = "",
    val pokemon: Pokemon? = null,
) : UIState

sealed interface PokemonDetailUIEvent : UIEvent