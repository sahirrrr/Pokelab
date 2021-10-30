package com.klikdigital.pokelab.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.klikdigital.pokelab.R
import com.klikdigital.pokelab.core.utils.Helper
import com.klikdigital.pokelab.databinding.ItemPokemonBinding
import com.klikdigital.pokelab.domain.model.PokemonListModel

class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    private var pokemonList = ArrayList<PokemonListModel>()

    fun setData(newPokemonListData : List<PokemonListModel>?) {
        if (newPokemonListData == null) return
        pokemonList.clear()
        pokemonList.addAll(newPokemonListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListAdapter.PokemonListViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.PokemonListViewHolder, position: Int) {
        val data = pokemonList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = pokemonList.size

    inner class PokemonListViewHolder(private val binding : ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon : PokemonListModel) {
            with(binding) {
                tvPokemonName.text = pokemon.name
                Glide.with(itemView.context)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + Helper.getImageIDFromURL(pokemon.url) + ".png")
                    .into(ivPokemon)

                itemView.setOnClickListener { view ->
                    view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                }
            }

        }
    }

}