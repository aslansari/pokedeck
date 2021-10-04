package com.aslansari.pokedeck

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.aslansari.pokedeck.network.PagedResponse
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.Deck
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pokemonCards: MutableList<Pokemon>? = null
        setContent {
            PokeDeckTheme {
                // A surface container using the 'background' color from the theme
                pokemonCards = remember { mutableStateListOf() }
                Deck(pokeCards = pokemonCards!!)
            }
        }
        val pokeDeckApp = application as PokeDeckApp
        val pokemonViewModel = pokeDeckApp.appContainer.pokemonViewModel

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            pokemonCards?.addAll(pokemonViewModel.getPokemonList())
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    PokeDeckTheme {
        val pokemonCards = mutableListOf<Pokemon>()
        pokemonCards.apply {
            add(Pokemon("Pikachu"))
            add(Pokemon("Charmander"))
            add(Pokemon("Bulbasaur"))
        }
        Deck(pokeCards = pokemonCards)
    }
}