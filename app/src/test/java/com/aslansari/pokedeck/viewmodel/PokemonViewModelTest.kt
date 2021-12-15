package com.aslansari.pokedeck.viewmodel

import com.aslansari.pokedeck.network.PagedResponse
import com.aslansari.pokedeck.network.PokemonService
import com.aslansari.pokedeck.network.Result
import com.aslansari.pokedeck.pokemon.dto.PokemonDTO
import com.aslansari.pokedeck.pokemon.dto.Sprites
import com.aslansari.pokedeck.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class PokemonViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: PokemonViewModel
    private lateinit var pokemonService: PokemonService
    @Before
    fun setup() {

        pokemonService = mock {
            onBlocking { getPokemonList() } doReturn PagedResponse(1,"","", fakePokemonList())
            onBlocking { getPokemon(anyString()) } doReturn fakePokemon().copy(name = "bulbasaur")
        }

        val pokemonRepository = PokemonRepository(pokemonService)
        viewModel = PokemonViewModel(mainCoroutineRule.testDispatcher, pokemonRepository)
    }

    private fun fakePokemonList(): List<Result> {
        val pokemonList = mutableListOf<Result>()
        pokemonList.add(Result("bulbasaur","https://pokeapi.co/api/v2/pokemon/1/"))
        pokemonList.add(Result("ivysaur","https://pokeapi.co/api/v2/pokemon/2/"))
        return pokemonList
    }

    private fun fakePokemon(): PokemonDTO {
        return PokemonDTO(
            name = "bulbasaur",
            id= 0,
            baseExperience = 0,
            height = 0,
            isDefault = false,
            weight = 0,
            sprites = Sprites("","")
        )
    }

    @Test
    fun `Testing fetching pokemon`(): Unit = mainCoroutineRule.testDispatcher.runBlockingTest {
        viewModel.getPokemonList()
        val testResults = mutableListOf<MutableList<PokemonDTO>>()
        val job = launch {
            viewModel.pokemonFlow.toList(testResults.toMutableList())
        }
        verify(pokemonService) {
            1 * { getPokemonList()}
            2 * { getPokemon(anyString())}
        }
        job.cancel()
    }

    /**
     * Although it looks deprecated it is in Google's current documentation
     */
    class MainCoroutineRule(
        val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    ) : TestWatcher() {

        override fun starting(description: Description?) {
            super.starting(description)
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description?) {
            super.finished(description)
            Dispatchers.resetMain()
            testDispatcher.cleanupTestCoroutines()
        }
    }
}