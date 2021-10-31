package com.klikdigital.pokelab.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.klikdigital.pokelab.core.data.source.remote.response.Chain

@Entity(tableName = "evolution_pokemon_table")
data class EvolutionPokemonEntity(
    @PrimaryKey
    val id: Int,

    val chain: Chain,
)