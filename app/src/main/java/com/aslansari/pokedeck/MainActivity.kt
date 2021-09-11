package com.aslansari.pokedeck

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.aslansari.pokedeck.network.Page
import com.aslansari.pokedeck.network.PagedResponse
import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.network.RetrofitClient
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.pokemon.PokemonRepository
import com.aslansari.pokedeck.ui.Deck
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val service = RetrofitClient.getClient().create(PokemonService::class.java)

        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            val response:PagedResponse = service.getPokemonList()
            for (result in response.results) {
                pokemonCards?.add(Pokemon(result.name.replaceFirstChar(Char::uppercase)))
            }
        }
    }
}

@Preview(showBackground = true)
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