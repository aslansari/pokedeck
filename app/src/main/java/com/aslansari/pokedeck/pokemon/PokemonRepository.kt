package com.aslansari.pokedeck.pokemon

import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.network.RetrofitClient

class PokemonRepository {
    var client: PokemonService = RetrofitClient.getClient().create(PokemonService::class.java)

    suspend fun getPokemonList() = client.getPokemonList()
}