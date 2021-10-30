package com.klikdigital.pokelab.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(

	@field:SerializedName("results")
	val results: List<ResultsItem?>
)

data class ResultsItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("url")
	val url: String
)

