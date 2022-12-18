@file:OptIn(ExperimentalAnimationApi::class)

package com.aslansari.pokedeck.feature.pokemon

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.PokeCard
import com.aslansari.pokedeck.util.items
import com.aslansari.pokedeck.viewmodel.PokemonListUIEvent
import com.aslansari.pokedeck.viewmodel.PokemonListUIState
import com.aslansari.pokedeck.viewmodel.PokemonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel,
    navigateToDetails: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val pokemonPages = uiState.pokemonFlow?.collectAsLazyPagingItems(Dispatchers.IO)

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is PokemonListUIEvent.NavigateToDetails -> navigateToDetails(event.pokemonId)
                    else -> {}
                }
            }
        }
    }

    Scaffold { padding ->
        Content(
            modifier = Modifier.padding(padding),
            uiState = uiState,
            pokemonItems = pokemonPages,
            onClick = viewModel::onPokemonClick,
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: PokemonListUIState,
    pokemonItems: LazyPagingItems<Pokemon>?,
    onClick: (String) -> Unit
) {
    val loadingState by remember {
        derivedStateOf {
            pokemonItems?.loadState?.refresh is LoadState.Loading
        }
    }
    AnimatedContent(targetState = loadingState) { loading ->
        if (loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            pokemonItems?.let { pokemonItems ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    items(pokemonItems) { pokemon ->
                        pokemon?.let {
                            PokeCard(
                                modifier = Modifier.padding(8.dp),
                                pokemon = pokemon,
                                onClick = onClick
                            )
                        }
                    }
                }
            }
        }
    }
}
