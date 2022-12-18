package com.aslansari.pokedeck.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BlueGray800,
    onPrimary = Color.White,
    primaryVariant = Gray900,
    secondary = LightBlue900,
    onSecondary = Color.White,
    surface = LightBlue900,
    onSurface = Color.White,
    background = BlueGray900,
    onBackground = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Gray50,
    primaryVariant = LightBlue700,
    secondary = Red400,
    onPrimary = Color.Black,
    surface = Gray50,
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Gray900,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PokeDeckTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}