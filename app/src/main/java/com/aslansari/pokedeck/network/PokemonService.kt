package com.aslansari.pokedeck.network

import com.aslansari.pokedeck.pokemon.Ability
import com.aslansari.pokedeck.pokemon.dto.PokemonDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): PokemonListResponse

    @GET()
    suspend fun getPokemonById(@Url url: String): PokemonDTO

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonDTO

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDTO

    @GET("ability/{id}")
    suspend fun getAbility(@Path("id") id: Int): Ability

    @GET("ability/{name}")
    suspend fun getAbilityByName(@Path("name") name: String): Ability
}