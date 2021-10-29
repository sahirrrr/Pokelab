package com.klikdigital.pokelab.domain.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListModel (
    val name: String,

    val url: String
): Parcelable