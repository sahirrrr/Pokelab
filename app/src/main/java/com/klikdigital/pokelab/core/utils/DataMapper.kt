package com.klikdigital.pokelab.core.utils

import com.klikdigital.pokelab.core.data.source.local.entity.DetailPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity
import com.klikdigital.pokelab.core.data.source.remote.response.DetailPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import com.klikdigital.pokelab.domain.model.DetailPokemonModel
import com.klikdigital.pokelab.domain.model.PokemonListModel

object DataMapper {
    fun mapPokemonListResponseToEntitites(data : PokemonListResponse) : List<PokemonListEntity> {
        val pokemonList = ArrayList<PokemonListEntity>()
            data.results.map {
                with(it) {
                    val pokemon = PokemonListEntity(
                        this!!.name, url
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
                    name, url
                )
            }
        }
    }

    fun mapDetailPokemonResponseToEntities(data: DetailPokemonResponse) : List<DetailPokemonEntity> {
        val detailPokemon = ArrayList<DetailPokemonEntity>()
        with(data) {
            detailPokemon.add(
                DetailPokemonEntity(
                    id, name, height, weight, sprites, abilities, stats, types
                )
            )
        }
        return detailPokemon
    }

    fun mapDetailPokemonEntitiesToDomain(data: List<DetailPokemonEntity>) : List<DetailPokemonModel> {
        return data.map {
            with(it) {
                DetailPokemonModel(
                    id, name, height, weight, sprites, abilities, stats, types
                )
            }
        }
    }
}