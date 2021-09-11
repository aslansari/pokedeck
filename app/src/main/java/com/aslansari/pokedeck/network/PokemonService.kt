package com.aslansari.pokedeck.network

import com.aslansari.pokedeck.pokemon.Pokemon
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList():PagedResponse

    @GET()
    suspend fun getPokemon(@Url url:String):Pokemon
}