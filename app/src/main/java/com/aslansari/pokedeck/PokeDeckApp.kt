package com.aslansari.pokedeck

import android.app.Application
import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.network.RetrofitClient
import com.aslansari.pokedeck.repository.PokemonRepository
import com.aslansari.pokedeck.viewmodel.PokemonViewModel

class PokeDeckApp: Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
    }
}

class AppContainer {
    val pokemonAPIBaseURL = "https://pokeapi.co/api/v2/"
    private val pokemonRepository = PokemonRepository(client = RetrofitClient.getClient(baseUrl = pokemonAPIBaseURL).create(PokemonService::class.java))
    val pokemonViewModel = PokemonViewModel(pokemonRepository = pokemonRepository)
}