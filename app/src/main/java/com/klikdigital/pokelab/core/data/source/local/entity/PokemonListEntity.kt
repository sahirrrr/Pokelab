package com.klikdigital.pokelab.core.data.source.local.entity

import androidx.room.Entity

@Entity(tableName = "pokemon_list_table", primaryKeys = ["name", "url"])
data class PokemonListEntity (

    val name: String,

    val url: String
)