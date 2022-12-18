package com.aslansari.pokedeck.feature.pokemon

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.repository.PokemonPagingSource
import com.aslansari.pokedeck.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedPokemonListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonPagingSource(pokemonRepository)
            }
        ).flow
    }
}