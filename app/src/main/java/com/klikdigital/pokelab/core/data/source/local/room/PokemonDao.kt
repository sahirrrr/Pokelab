package com.klikdigital.pokelab.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonList(pokemonList : List<PokemonListEntity>) : Completable

    @Query("select * from pokemon_list_table")
    fun getPokemonList() : Flowable<List<PokemonListEntity>>
}