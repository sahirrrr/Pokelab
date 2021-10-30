package com.klikdigital.pokelab.core.data.source.local

import com.klikdigital.pokelab.core.data.source.local.entity.DetailPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity
import com.klikdigital.pokelab.core.data.source.local.room.PokemonDao
import io.reactivex.Flowable

class LocalDataSource(private val dao: PokemonDao) {

    fun insertPokemonList(pokemonList : List<PokemonListEntity>) = dao.insertPokemonList(pokemonList)

    fun getPokemonList() : Flowable<List<PokemonListEntity>> = dao.getPokemonList()

    fun insertDetailPokemon(pokemon : List<DetailPokemonEntity>) = dao.insertDetailPokemon(pokemon)

    fun getDetailPokemon(id : String) : Flowable<List<DetailPokemonEntity>> = dao.getDetailPokemon(id)
}