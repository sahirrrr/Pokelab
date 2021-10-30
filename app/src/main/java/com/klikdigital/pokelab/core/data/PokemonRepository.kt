package com.klikdigital.pokelab.core.data

import android.util.Log
import com.klikdigital.pokelab.core.data.source.local.LocalDataSource
import com.klikdigital.pokelab.core.data.source.remote.RemoteDataSource
import com.klikdigital.pokelab.core.data.source.remote.network.ApiResponse
import com.klikdigital.pokelab.core.data.source.remote.response.DetailPokemonResponse
import com.klikdigital.pokelab.core.data.source.remote.response.PokemonListResponse
import com.klikdigital.pokelab.core.utils.DataMapper
import com.klikdigital.pokelab.domain.model.DetailPokemonModel
import com.klikdigital.pokelab.domain.model.PokemonListModel
import com.klikdigital.pokelab.domain.repository.IPokemonRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IPokemonRepository {
    override fun getPokemonList(): Flowable<Resource<List<PokemonListModel>>> =
        object : NetworkBoundResource<List<PokemonListModel>, PokemonListResponse>() {
            override fun loadFromDB(): Flowable<List<PokemonListModel>> {
                Log.d("detail", "loadFromDBHome")
                return localDataSource.getPokemonList().map { DataMapper.mapPokemonListEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<PokemonListModel>?): Boolean {
                Log.d("detail", "shouldFetchHome: ${data == null || data.isEmpty()}")
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: PokemonListResponse) {
                Log.d("detail", "saveCallResultHome")
                val pokemonList = DataMapper.mapPokemonListResponseToEntitites(data)
                localDataSource.insertPokemonList(pokemonList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<PokemonListResponse>> {
                Log.d("detail", "createCallHome")
                return remoteDataSource.getPokemonList()
            }
        }.asFlowAble()

    override fun getDetailPokemon(id: String): Flowable<Resource<List<DetailPokemonModel>>> =
        object : NetworkBoundResource<List<DetailPokemonModel>, DetailPokemonResponse>() {
            override fun loadFromDB(): Flowable<List<DetailPokemonModel>> {
                Log.d("detail", "loadFromDB")
                 return localDataSource.getDetailPokemon(id).map { DataMapper.mapDetailPokemonEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<DetailPokemonModel>?): Boolean {
                Log.d("detail", "shouldFetch: ${data == null}")
                return data == null || data.isEmpty()
            }

            override fun saveCallResult(data: DetailPokemonResponse) {
                Log.d("detail", "saveCallResult")
                val detailPokemon = DataMapper.mapDetailPokemonResponseToEntities(data)
                localDataSource.insertDetailPokemon(detailPokemon)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            override fun createCall(): Flowable<ApiResponse<DetailPokemonResponse>> {
                Log.d("detail", "createCall")
                return remoteDataSource.getDetailPokemon(id)
            }
        }.asFlowAble()

}