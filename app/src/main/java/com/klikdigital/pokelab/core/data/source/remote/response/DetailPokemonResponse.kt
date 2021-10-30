package com.klikdigital.pokelab.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DetailPokemonResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("height")
	val height: Int,

	@field:SerializedName("weight")
	val weight: Int,

	@field:SerializedName("sprites")
	val sprites: SpritesItem,

	@field:SerializedName("abilities")
	val abilities: List<AbilitiesItem>,

	@field:SerializedName("stats")
	val stats: List<StatsItem>,

	@field:SerializedName("types")
	val types: List<TypesItem>,
)

@Parcelize
data class SpritesItem (
	@field:SerializedName("front_default")
	val pokeImage: String
): Parcelable

@Parcelize
data class AbilitiesItem(

	@field:SerializedName("ability")
	val ability: Ability,
): Parcelable

@Parcelize
data class Ability(

	@field:SerializedName("name")
	val name: String,
): Parcelable

@Parcelize
data class StatsItem(

	@field:SerializedName("stat")
	val stat: Stat,

	@field:SerializedName("base_stat")
	val baseStat: Int,
): Parcelable

@Parcelize
data class Stat(

	@field:SerializedName("name")
	val name: String,
): Parcelable

@Parcelize
data class TypesItem(

	@field:SerializedName("type")
	val type: Type
): Parcelable

@Parcelize
data class Type(

	@field:SerializedName("name")
	val name: String,
): Parcelable
