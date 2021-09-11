package com.aslansari.pokedeck.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
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
        fontFamily = rubikFamily,
        fontSize = 98.sp,
        letterSpacing = (-0.09375).em,
    ),
    h2 = TextStyle(
        fontFamily = rubikFamily,
        fontSize = 61.sp,
        letterSpacing = (-0.03125).em,
    ),
    h3 = TextStyle(
        fontFamily = rubikFamily,
        fontSize = 49.sp,
        letterSpacing = (0).em,
    ),
    h4 = TextStyle(
        fontFamily = rubikFamily,
        fontSize = 35.sp,
        letterSpacing = (0.015625).em,
    ),
    h5 = TextStyle(
        fontFamily = rubikFamily,
        fontSize = 24.sp,
        letterSpacing = (0).em,
    ),
    h6 = TextStyle(
        fontFamily = rubikFamily,
        fontSize = 20.sp,
        letterSpacing = (0.009375).em,
    ),
    subtitle1 = TextStyle(
        fontFamily = rubikFamily,
        fontSize = 16.sp,
        letterSpacing = (0.009375).em,
    ),
    subtitle2 = TextStyle(
        fontFamily = rubikFamily,
        fontSize = 14.sp,
        letterSpacing = (0.00625).em,
    ),
    body1 = TextStyle(
        fontFamily = rubikFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = (0.03125).em,
    ),
    body2 = TextStyle(
        fontFamily = rubikFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (0.015625).em,
    ),
    button = TextStyle(
        fontFamily = rubikFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = (0.078125).em
    ),
    caption = TextStyle(
        fontFamily = rubikFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = (0.025).em
    ),
    overline = TextStyle(
        fontFamily = rubikFamily,
        fontSize = 10.sp,
        letterSpacing = (0.09375).em
    ),
)


