package com.aslansari.pokedeck

import android.app.Application
import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.network.RetrofitClient
import com.aslansari.pokedeck.repository.PokemonRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokeDeckApp: Application() {

    val pokemonService by lazy {
        RetrofitClient.getClient(baseUrl = BuildConfig.API_BASE_URL).create(PokemonService::class.java)
    }
    val pokemonRepository by lazy { PokemonRepository(pokemonService) }

}
