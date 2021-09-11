package com.aslansari.pokedeck.network

import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList():PagedResponse
}