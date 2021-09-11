package com.aslansari.pokedeck.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import com.aslansari.pokedeck.R

val rubikFamily = FontFamily(
    Font(R.font.rubik_light, FontWeight.Light),
    Font(R.font.rubik_regular, FontWeight.Normal),
    Font(R.font.rubik_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.rubik_medium, FontWeight.Medium),
    Font(R.font.rubik_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = rubikFamily
    ),
    h2 = TextStyle(
        fontFamily = rubikFamily
    ),
    h3 = TextStyle(
        fontFamily = rubikFamily
    ),
    h4 = TextStyle(
        fontFamily = rubikFamily
    ),
    h5 = TextStyle(
        fontFamily = rubikFamily
    ),
    h6 = TextStyle(
        fontFamily = rubikFamily
    ),
    subtitle1 = TextStyle(
        fontFamily = rubikFamily
    ),
    subtitle2 = TextStyle(
        fontFamily = rubikFamily
    ),
    body1 = TextStyle(
        fontFamily = rubikFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = rubikFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = rubikFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = rubikFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = rubikFamily,
    ),
)


