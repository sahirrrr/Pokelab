package com.klikdigital.pokelab.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.klikdigital.pokelab.core.data.source.local.entity.DetailPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.EvolutionPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonSpeciesEntity

@Database(entities = [PokemonListEntity::class, DetailPokemonEntity::class, EvolutionPokemonEntity::class, PokemonSpeciesEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class PokemonDB : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao
}