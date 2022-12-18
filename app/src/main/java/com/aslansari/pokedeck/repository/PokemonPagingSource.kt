package com.aslansari.pokedeck.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aslansari.pokedeck.pokemon.Pokemon
import com.aslansari.pokedeck.pokemon.dto.PokemonDTO
import com.aslansari.pokedeck.pokemon.dto.toModel
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { position ->
            Log.d("Paging", "getRefreshKey $position")
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 1
        val offset = params.key?.let { it.plus(1) * params.loadSize } ?: 0
        return try {
            val responseList =
                pokemonRepository.getPokemonList(offset = offset, limit = params.loadSize)
            LoadResult.Page(
                data = responseList.map(PokemonDTO::toModel),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}