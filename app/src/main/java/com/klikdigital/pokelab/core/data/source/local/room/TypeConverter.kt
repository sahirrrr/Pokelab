package com.klikdigital.pokelab.core.data.source.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.klikdigital.pokelab.core.data.source.remote.response.*

class TypeConverter {

    @TypeConverter
    fun evolutionChainToString(value: EvolutionChain) = Gson().toJson(value)!!

    @TypeConverter
    fun StringToEvolutionChain(value: String) = Gson().fromJson(value, EvolutionChain::class.java)!!

    @TypeConverter
    fun chainToString(value: Chain) = Gson().toJson(value)!!

    @TypeConverter
    fun StringToChain(value: String) = Gson().fromJson(value,Chain::class.java)

    @TypeConverter
    fun speciesToString(value: Species) = Gson().toJson(value)!!

    @TypeConverter
    fun EvolutionDetailsToJson(value: List<EvolutionDetailsItem>) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonEvolutionDetailsToList(value: String) = Gson().fromJson(value, Array<EvolutionDetailsItem>::class.java).toList()

    @TypeConverter
    fun spritesToString(value: SpritesItem) = Gson().toJson(value)!!

    @TypeConverter
    fun stringToSprites(value: String) = Gson().fromJson(value, SpritesItem::class.java)!!

    @TypeConverter
    fun listOfAbilitiesToJson(value: List<AbilitiesItem>) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonAbilitiesToList(value: String) = Gson().fromJson(value, Array<AbilitiesItem>::class.java).toList()

    @TypeConverter
    fun listOfStatsToJson(value: List<StatsItem>) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonStatsToList(value: String) = Gson().fromJson(value, Array<StatsItem>::class.java).toList()

    @TypeConverter
    fun listOfTypesToJson(value: List<TypesItem>) = Gson().toJson(value)!!

    @TypeConverter
    fun jsonTypesToList(value: String) = Gson().fromJson(value, Array<TypesItem>::class.java).toList()


}