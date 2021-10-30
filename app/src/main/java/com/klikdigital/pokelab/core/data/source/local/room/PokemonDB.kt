package com.klikdigital.pokelab.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.klikdigital.pokelab.core.data.source.local.entity.DetailPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity

@Database(entities = [PokemonListEntity::class, DetailPokemonEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class PokemonDB : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao
}