package com.aslansari.pokedeck.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme
import com.aslansari.pokedeck.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : ComponentActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModels()

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
                            data = pokemon?.frontDefaultUrl,
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
                        val abilityList: List<String> = pokemon?.abilities?.map { it.name } ?: listOf()
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

        lifecycleScope.launchWhenStarted {
            pokemonViewModel.getPokemonList()
            pokemonViewModel.pokemonFlow.collect {
                if (it.isError.not().and(it.isLoading.not())) {
                    it.pokemonList.single { pokemon ->
                        pokemon.name == pokeName
                    }
                }
            }
        }
    }
}
