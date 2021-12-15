package com.aslansari.pokedeck

import android.app.Application
import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.network.RetrofitClient
import com.aslansari.pokedeck.repository.PokemonRepository
import com.aslansari.pokedeck.viewmodel.PokemonViewModel

class PokeDeckApp: Application() {

    private val pokemonAPIBaseURL = "https://pokeapi.co/api/v2/"
    val pokemonService by lazy { RetrofitClient.getClient(baseUrl = pokemonAPIBaseURL).create(PokemonService::class.java) }
    val pokemonRepository by lazy { PokemonRepository(pokemonService) }

}
