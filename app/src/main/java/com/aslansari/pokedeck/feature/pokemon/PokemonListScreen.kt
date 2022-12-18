package com.aslansari.pokedeck.feature.pokemon

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.aslansari.pokedeck.ui.Deck
import com.aslansari.pokedeck.viewmodel.PokemonListUIEvent
import com.aslansari.pokedeck.viewmodel.PokemonListUIState
import com.aslansari.pokedeck.viewmodel.PokemonViewModel
import kotlinx.coroutines.launch

@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel,
    navigateToDetails: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is PokemonListUIEvent.NavigateToDetails -> navigateToDetails(event.pokemonId)
                }
            }
        }
    }

    Scaffold { padding ->
        Content(
            modifier = Modifier.padding(padding),
            uiState = uiState,
            onClick = viewModel::onPokemonClick,
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: PokemonListUIState,
    onClick: (String) -> Unit
) {
    Deck(
        pokeCards = uiState.pokemonList,
        onClick = onClick
    )
}