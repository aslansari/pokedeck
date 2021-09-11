package com.aslansari.pokedeck.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme

internal class PokemonProvider: PreviewParameterProvider<Pokemon> {
    override val values = sequenceOf(Pokemon(name = "Pikachu"))
}

@OptIn(ExperimentalCoilApi::class)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview
@Composable
fun PokeCard (@PreviewParameter(PokemonProvider::class) pokemon: Pokemon) {
    PokeDeckTheme {
        Card(
            modifier = Modifier.fillMaxWidth(),
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
                        data = pokemon.sprites?.frontDefaultUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = "",
                    modifier = Modifier.weight(2f).size(128.dp)
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


@Composable
fun Deck(pokeCards: List<Pokemon>) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
    ){
        items(items = pokeCards) {
            PokeCard(pokemon = it)
        }
    }
}


