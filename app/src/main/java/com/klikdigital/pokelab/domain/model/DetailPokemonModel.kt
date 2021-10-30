package com.klikdigital.pokelab.domain.model

import android.os.Parcelable
import com.klikdigital.pokelab.core.data.source.remote.response.AbilitiesItem
import com.klikdigital.pokelab.core.data.source.remote.response.SpritesItem
import com.klikdigital.pokelab.core.data.source.remote.response.StatsItem
import com.klikdigital.pokelab.core.data.source.remote.response.TypesItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailPokemonModel (
    val id: Int,

    val name: String,

    val height: Int,

    val weight: Int,

    val sprites: SpritesItem,

    val abilities: List<AbilitiesItem>,

    val stats: List<StatsItem>,

    val types: List<TypesItem>,
): Parcelable