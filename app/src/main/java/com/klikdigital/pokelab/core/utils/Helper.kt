package com.klikdigital.pokelab.core.utils

object Helper {
    fun getImageIDFromURL(url: String?) : String? {
        val newUrl = url?.substringAfter("/pokemon/")
        return newUrl?.substringBeforeLast("/")
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