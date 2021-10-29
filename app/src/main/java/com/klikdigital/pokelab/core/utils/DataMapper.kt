package com.klikdigital.pokelab.core.utils

import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import com.klikdigital.pokelab.domain.model.PokemonListModel

object DataMapper {
    fun mapPokemonListResponseToEntitites(data : List<PokemonListResponse>) : List<PokemonListEntity> {
        val pokemonList = ArrayList<PokemonListEntity>()
        data.map {
            with(it) {
                val pokemon = PokemonListEntity(
                    name, url
                )
                pokemonList.add(pokemon)
            }
        }
        return pokemonList
    }

    fun mapPokemonListEntitiesToDomain(data : List<PokemonListEntity>) : List<PokemonListModel> {
        return data.map {
            with(it) {
                PokemonListModel(
                    name,url
                )
            }
        }
    }
}