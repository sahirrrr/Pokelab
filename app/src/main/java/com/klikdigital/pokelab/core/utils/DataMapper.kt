package com.klikdigital.pokelab.core.utils

import com.klikdigital.pokelab.core.data.source.local.entity.DetailPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.EvolutionPokemonEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonListEntity
import com.klikdigital.pokelab.core.data.source.local.entity.PokemonSpeciesEntity
import com.klikdigital.pokelab.core.data.source.remote.response.DetailPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.EvolutionPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonSpeciesResponse
import com.klikdigital.pokelab.domain.model.DetailPokemonModel
import com.klikdigital.pokelab.domain.model.EvolutionPokemonModel
import com.klikdigital.pokelab.domain.model.PokemonListModel
import com.klikdigital.pokelab.domain.model.PokemonSpeciesModel

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

    fun mapEvolutionPokemonResponseToEntities(data: EvolutionPokemonResponse) : List<EvolutionPokemonEntity> {
        val evolutionPokemon = ArrayList<EvolutionPokemonEntity>()
        with(data) {
            evolutionPokemon.add(
                EvolutionPokemonEntity(
                    id, chain
                )
            )
        }
        return evolutionPokemon
    }

    fun mapEvolutionPokemonEntitiesToDomain(data: List<EvolutionPokemonEntity>) : List<EvolutionPokemonModel> {
        return data.map {
            with(it) {
                EvolutionPokemonModel(
                    id, chain
                )
            }
        }
    }

    fun mapPokemonSpeciesResponseToEntities(data: PokemonSpeciesResponse) : List<PokemonSpeciesEntity> {
        val pokemonSpecies = ArrayList<PokemonSpeciesEntity>()
        with(data) {
            pokemonSpecies.add(
                PokemonSpeciesEntity(
                    id, evolutionChain
                )
            )
        }
        return pokemonSpecies
    }

    fun mapPokemonSpeciesEntitiesToDomain(data: List<PokemonSpeciesEntity>) : List<PokemonSpeciesModel> {
        return data.map {
            with(it) {
                PokemonSpeciesModel(
                    id, evolutionChain
                )
            }
        }
    }


}