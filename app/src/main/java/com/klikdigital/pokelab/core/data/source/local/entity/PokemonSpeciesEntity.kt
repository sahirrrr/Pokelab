package com.klikdigital.pokelab.core.data.source.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.klikdigital.pokelab.core.data.source.remote.response.EvolutionChain

@Entity(tableName = "pokemon_species_table")
data class PokemonSpeciesEntity (
    @PrimaryKey
    val id: Int,

    val evolutionChain: EvolutionChain,
)