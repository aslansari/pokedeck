package com.aslansari.pokedeck.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aslansari.pokedeck.pokemon.Pokemon

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
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.size(200.dp),
            model = uiState.pokemon?.frontDefaultUrl.orEmpty(),
            contentDescription = ""
        )
        Text(
            text = uiState.name.replaceFirstChar(Char::uppercase),
            style = MaterialTheme.typography.h4
        )
        uiState.pokemon?.let {
            PokemonStats(pokemon = uiState.pokemon)
        }
    }
}

@Composable
private fun PokemonStats(
    pokemon: Pokemon
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Stats",
            style = MaterialTheme.typography.h4
        )
        val stats by remember { derivedStateOf { pokemon.stats.associateBy { it.name } }}
        stats["hp"]?.let {
            Text(
                text = "HP ${it.stat}",
                style = MaterialTheme.typography.subtitle1
            )
        }
        stats["attack"]?.let {
            Text(
                text = "Attack ${it.stat}",
                style = MaterialTheme.typography.subtitle1
            )
        }
        stats["defense"]?.let {
            Text(
                text = "Defense ${it.stat}",
                style = MaterialTheme.typography.subtitle1
            )
        }
        stats["speed"]?.let {
            Text(
                text = "Speed ${it.stat}",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}