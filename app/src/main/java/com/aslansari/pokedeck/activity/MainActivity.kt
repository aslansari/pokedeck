package com.aslansari.pokedeck.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.aslansari.pokedeck.navigation.MainNavGraph
import com.aslansari.pokedeck.ui.theme.PokeDeckTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDeckTheme {
                MainNavGraph(modifier = Modifier)
            }
        }
    }
}