package com.aslansari.pokedeck.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.coroutineScope
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.Deck
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme
import com.aslansari.pokedeck.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModels()

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