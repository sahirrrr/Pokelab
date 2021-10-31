package com.klikdigital.pokelab.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.klikdigital.pokelab.core.data.source.remote.response.TypesItem
import com.klikdigital.pokelab.core.utils.Helper
import com.klikdigital.pokelab.databinding.ItemTypePokemonBinding

class PokemonTypeAdapter : RecyclerView.Adapter<PokemonTypeAdapter.PokemonTypeViewHolder>() {

    private var pokemonTypeList = ArrayList<TypesItem>()

    fun setData(newPokemonTypeData: List<TypesItem>?) {
        if (newPokemonTypeData == null) return
        pokemonTypeList.clear()
        pokemonTypeList.addAll(newPokemonTypeData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeAdapter.PokemonTypeViewHolder {
        val binding = ItemTypePokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonTypeAdapter.PokemonTypeViewHolder, position: Int) {
        val data = pokemonTypeList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = pokemonTypeList.size

    inner class PokemonTypeViewHolder(private val binding : ItemTypePokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonType: TypesItem) {
            with(binding) {
                    tvPokemonType.text = Helper.capitalizeFirstWord(pokemonType.type.name)
            }
        }
    }
}