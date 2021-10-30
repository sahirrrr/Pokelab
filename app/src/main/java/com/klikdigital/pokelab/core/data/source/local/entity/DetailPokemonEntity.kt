package com.klikdigital.pokelab.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.klikdigital.pokelab.core.data.source.remote.response.AbilitiesItem
import com.klikdigital.pokelab.core.data.source.remote.response.SpritesItem
import com.klikdigital.pokelab.core.data.source.remote.response.StatsItem
import com.klikdigital.pokelab.core.data.source.remote.response.TypesItem

@Entity(tableName = "detail_pokemon_table")
data class DetailPokemonEntity (
    @PrimaryKey
    val id: Int,

    val name: String,

    val height: Int,

    val weight: Int,

    val sprites: SpritesItem,

    val abilities: List<AbilitiesItem>,

    val stats: List<StatsItem>,

    val types: List<TypesItem>,
)