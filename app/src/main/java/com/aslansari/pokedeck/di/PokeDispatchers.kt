package com.aslansari.pokedeck.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val ctDispatcher: PokeDispatchers)

enum class PokeDispatchers {
    IO
}
