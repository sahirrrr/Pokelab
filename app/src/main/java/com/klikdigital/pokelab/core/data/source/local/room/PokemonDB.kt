package com.klikdigital.pokelab.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity

@Database(entities = [PokemonListEntity::class], version = 1, exportSchema = false)
abstract class PokemonDB : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao
}