package com.klikdigital.pokelab.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.klikdigital.pokelab.core.data.source.local.entity.DetailPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.EvolutionPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonSpeciesEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonList(pokemonList : List<PokemonListEntity>) : Completable

    @Query("select * from pokemon_list_table")
    fun getPokemonList() : Flowable<List<PokemonListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailPokemon(pokemon : List<DetailPokemonEntity>) : Completable

    @Query("select * from detail_pokemon_table where id = :id")
    fun getDetailPokemon(id: String) : Flowable<List<DetailPokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvolutionPokemon(pokemon : List<EvolutionPokemonEntity>) : Completable

    @Query("select * from evolution_pokemon_table where id = :id")
    fun getEvolutionPokemon(id: String) : Flowable<List<EvolutionPokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonSpecies(pokemon : List<PokemonSpeciesEntity>) : Completable

    @Query("select * from pokemon_species_table where id = :id")
    fun getPokemonSpecies(id: String) : Flowable<List<PokemonSpeciesEntity>>

}