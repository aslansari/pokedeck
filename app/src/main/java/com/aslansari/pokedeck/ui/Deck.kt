package com.aslansari.pokedeck.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme

@Preview
@Composable
fun PokeCardPreview() {
    PokeCard(pokemon = Pokemon(name = "Charmander"), onClick = {})
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PokeCard (pokemon: Pokemon, onClick: (name: String) -> Unit) {
    PokeDeckTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onClick(pokemon.name) }),
            elevation = 1.dp,
            shape = RoundedCornerShape(4.dp),
            backgroundColor = MaterialTheme.colors.primary,
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = pokemon.frontDefaultUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .weight(2f)
                        .size(128.dp)
                )
                Text(
                    text = pokemon.name.replaceFirstChar(Char::uppercase),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(3f),
                )
            }
        }
    }
}

@Preview
@Composable
fun DeckPreview() {
    Deck(pokeCards = getFakePokemonData(), onClick = {})
}

@Composable
fun Deck(pokeCards: List<Pokemon>, onClick: (name: String) -> Unit) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
    ){
        items(items = pokeCards) {
            PokeCard(pokemon = it, onClick)
        }
    }
}

fun getFakePokemonData(): MutableList<Pokemon> {
    val pokemons = mutableListOf<Pokemon>()
    pokemons.apply {
        add(Pokemon("Pikachu"))
        add(Pokemon("Charmander"))
        add(Pokemon("Bulbasaur"))
    }
    return pokemons
}


