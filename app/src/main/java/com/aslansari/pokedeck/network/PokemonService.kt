package com.aslansari.pokedeck.network

import com.aslansari.pokedeck.pokemon.Ability
import com.aslansari.pokedeck.pokemon.dto.PokemonDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList():PagedResponse

    @GET()
    suspend fun getPokemon(@Url url:String): PokemonDTO

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonDTO

    @GET("ability/{id}")
    suspend fun getAbility(@Path("id") id: Int): Ability

    @GET("ability/{name}")
    suspend fun getAbilityByName(@Path("name") name: String): Ability
}