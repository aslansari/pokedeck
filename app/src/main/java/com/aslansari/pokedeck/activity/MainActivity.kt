package com.aslansari.pokedeck.activity

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.coroutineScope
import com.aslansari.pokedeck.PokeDeckApp
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.Deck
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme
import com.aslansari.pokedeck.viewmodel.PokemonViewModel
import com.aslansari.pokedeck.viewmodel.PokemonViewModelFactory
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModels(factoryProducer = {
        PokemonViewModelFactory((application as PokeDeckApp).pokemonRepository)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pokemonCards: MutableList<Pokemon> = mutableListOf()
        setContent {
            PokeDeckTheme {
                // A surface container using the 'background' color from the theme
                pokemonCards = remember { mutableStateListOf() }
                Deck(pokeCards = pokemonCards, onClick = {
                    val intent = Intent(this, PokemonActivity::class.java).apply {
                        putExtra("name", it)
                    }
                    startActivity(intent)
                })
            }
        }


        lifecycle.coroutineScope.launchWhenStarted {
            pokemonViewModel.pokemonFlow.collect {
                if (it.isError.not().and(it.isLoading.not())) {
                    pokemonCards.addAll(it.pokemonList)
                }
            }
        }
        pokemonViewModel.getPokemonList()
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
        Deck(pokeCards = pokemonCards, onClick = {})
    }
}