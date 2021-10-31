package com.klikdigital.pokelab.ui.adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.klikdigital.pokelab.R
import com.klikdigital.pokelab.core.utils.Helper
import com.klikdigital.pokelab.databinding.ItemPokemonBinding
import com.klikdigital.pokelab.domain.model.PokemonListModel
import com.klikdigital.pokelab.ui.detail.DetailActivity
import kotlinx.coroutines.DelicateCoroutinesApi

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
                val id = Helper.getImageIDFromURLPokemon(pokemon.url)

                tvPokemonName.text = Helper.capitalizeFirstWord(pokemon.name)
                Glide.with(itemView.context)
                    .asBitmap()
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png")
                    .into(object : CustomTarget<Bitmap>(){
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            binding.ivPokemon.setImageBitmap(resource)

                            val vibrant = Helper.createPaletteSync(resource).vibrantSwatch
                            vibrant?.rgb?.let { ivPokeball.setColorFilter(it) }
                            ivPokeball.alpha = 0.18f
                        }
                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, id)
                    itemView.context.startActivity(intent)
                }
            }

        }
    }

}