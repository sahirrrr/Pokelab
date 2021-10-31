package com.klikdigital.pokelab.core.utils

import android.graphics.Bitmap
import androidx.palette.graphics.Palette
import java.util.*

object Helper {

    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    fun lvlConverter(level: Int) : String {
        return "Lvl $level"
    }

    fun capitalizeFirstWord(str: String) : String {
        return str.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }

    fun getImageIDFromURLPokemon(url: String?) : String? {
        val newUrl = url?.substringAfter("/pokemon/")
        return newUrl?.substringBeforeLast("/")
    }

    fun getImageIDFromURLEvolution(url: String?) : String? {
        val newUrl = url?.substringAfter("/pokemon-species/")
        return newUrl?.substringBeforeLast("/")
    }

    fun getSpeciesIDFromURLEvolution(url: String?) : String? {
        val newUrl = url?.substringAfter("/evolution-chain/")
        return newUrl?.substringBeforeLast("/")
    }

    fun getEvolutionURLFromSpecies(url: String?) : String? {
        return url?.substringAfter("https://pokeapi.co")
    }

    fun idConverters(id: Int) : String {
        return "#${String.format("%03d", id)}"
    }

    fun weightConverters(weight: Int) : String {
        val newWeight = weight.toDouble() / 10
        return "$newWeight kg"
    }

    fun heightConverters(height: Int) : String {
        val newHeight = height.toDouble() / 10
        return "$newHeight m"
    }
}