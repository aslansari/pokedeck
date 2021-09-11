package com.aslansari.pokedeck.ui

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.aslansari.pokedeck.R
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.theme.BlueGray50
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme

internal class PokemonProvider: PreviewParameterProvider<Pokemon> {
    override val values = sequenceOf(Pokemon(name = "Pikachu"))
}
@Preview
@Composable
fun PokeCard (@PreviewParameter(PokemonProvider::class) pokemon: Pokemon) {
    PokeDeckTheme {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 1.dp,
            shape = RoundedCornerShape(4.dp),
            backgroundColor = BlueGray50,
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(5.dp).weight(3f),
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


