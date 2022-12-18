package com.aslansari.pokedeck.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel,
) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold { padding ->
        Content(
            modifier = Modifier.padding(padding),
            uiState = uiState,
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: PokemonDetailUIState,
) {
    Column(modifier = modifier) {
        Text(text = uiState.name)
    }
}