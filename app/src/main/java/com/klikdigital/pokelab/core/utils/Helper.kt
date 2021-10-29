package com.klikdigital.pokelab.core.utils

object Helper {
    fun getImageIDFromURL(url: String?) : String? {
        val newUrl = url?.substringAfter("/pokemon/")
        return newUrl?.substringBeforeLast("/")
    }
}