@file:OptIn(ExperimentalAnimationApi::class)

package com.aslansari.pokedeck.feature.pokemon

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aslansari.pokedeck.ui.Deck
import com.aslansari.pokedeck.ui.PokeCard
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
    AnimatedContent(targetState = uiState.loading) { loading ->
        if (loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Deck(
                pokemonList = uiState.pokemonList,
                onClick = onClick
            )
        }
    }
}