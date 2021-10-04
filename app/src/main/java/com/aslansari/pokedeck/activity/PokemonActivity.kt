package com.aslansari.pokedeck.activity

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.aslansari.pokedeck.PokeDeckApp
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.PokeCard
import com.aslansari.pokedeck.ui.PokemonProvider
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme
import kotlinx.coroutines.runBlocking

class PokemonActivity : ComponentActivity() {
    lateinit var pokeName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.hasExtra("name")) {
            pokeName = intent.getStringExtra("name") ?: ""
        }
        var pokemon: Pokemon? = null
        setContent {
            PokeDeckTheme {
                // A surface container using the 'background' color from the theme
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxSize()
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = pokemon?.sprites?.frontDefaultUrl,
                            builder = {
                                transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = "",
                        modifier = Modifier.size(250.dp)
                    )
                    Text(
                        text = pokeName.replaceFirstChar(Char::uppercase),
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(5.dp),
                        color = contentColorFor(backgroundColor = MaterialTheme.colors.background)
                    )
                    LazyColumn (
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
                    ){
                        val abilityList: List<String> = pokemon?.abilities?.map { it.ability.name } ?: listOf()
                        items(items = abilityList) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.padding(5.dp),
                                color = contentColorFor(backgroundColor = MaterialTheme.colors.background)
                            )
                        }
                    }
                }
            }
        }

        val pokeDeckApp = application as PokeDeckApp
        val pokemonViewModel = pokeDeckApp.appContainer.pokemonViewModel
        runBlocking {
            pokemon = pokemonViewModel.getPokemonList().single {
                it.name == pokeName
            }
        }
    }
}
