package com.aslansari.pokedeck.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme

@Composable
fun PokeCard(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onClick: (name: String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(pokemon.name) }),
        elevation = 1.dp,
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Image(
                modifier = Modifier.size(128.dp),
                painter = rememberImagePainter(
                    data = pokemon.frontDefaultUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = "",
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = pokemon.name.replaceFirstChar(Char::uppercase),
                style = MaterialTheme.typography.h5,
            )
        }
    }
}

@Preview
@Composable
fun PokeCardPreview() {
    PokeDeckTheme {
        PokeCard(pokemon = Pokemon(name = "Charmander"), onClick = {})
    }
}
