package com.klikdigital.pokelab.core.data.source.local.room

import android.animation.ValueAnimator
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.klikdigital.pokelab.core.data.source.remote.response.AbilitiesItem
import com.klikdigital.pokelab.core.data.source.remote.response.SpritesItem
import com.klikdigital.pokelab.core.data.source.remote.response.StatsItem
import com.klikdigital.pokelab.core.data.source.remote.response.TypesItem

class TypeConverter {

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