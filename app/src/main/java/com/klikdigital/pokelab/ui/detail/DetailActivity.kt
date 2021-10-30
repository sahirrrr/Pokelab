package com.klikdigital.pokelab.ui.detail

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.klikdigital.pokelab.R
import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.databinding.ActivityDetailBinding
import com.klikdigital.pokelab.ui.adapter.PokemonTypeAdapter
import com.klikdigital.pokelab.ui.adapter.SectionsPagerAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import com.klikdigital.pokelab.core.utils.Helper
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )

        const val EXTRA_ID = "extra_id"
    }

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // hide action bar
        supportActionBar?.hide()

        //transparent status bar
        setTransparentStatusBar()

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2? = binding?.viewPager
        viewPager?.adapter = sectionsPagerAdapter

        val tabs: TabLayout? = binding?.tabs
        if (tabs != null && viewPager != null) {
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        val id = intent.getStringExtra(EXTRA_ID)
        if (id != null) {
            pokemonDetail(id)
        }

        binding?.icBack?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun pokemonDetail(id: String) {
        val pokemonTypeAdapter = PokemonTypeAdapter()
        viewModel.getDetailPokemon(id).observe(this, { detailPokemon ->
            if (detailPokemon != null) {
                when(detailPokemon) {
                    is Resource.Success -> {
                        val dataDetail = detailPokemon.data!!
                        for (data in dataDetail.indices) {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.tvPokemonName?.text = dataDetail[data].name
                            binding?.tvPokemonId?.text = Helper.idConverters(dataDetail[data].id)
                            binding?.tvPokemonHeight?.text = Helper.heightConverters(dataDetail[data].height)
                            binding?.tvPokemonWeight?.text = Helper.weightConverters(dataDetail[data].weight)

                            binding?.ivPokemon?.let {
                                Glide.with(this)
                                    .load(dataDetail[data].sprites.pokeImage)
                                    .into(it)
                            }
                            val dataArray = dataDetail[data].types
                            pokemonTypeAdapter.setData(dataArray)
                            pokemonTypeAdapter.notifyDataSetChanged()
                        }
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })

        with(binding?.rvTypePokemon) {
            this?.layoutManager = LinearLayoutManager(DetailActivity(), LinearLayoutManager.HORIZONTAL, false)
            this?.setHasFixedSize(true)
            this?.adapter = pokemonTypeAdapter
        }
    }

    private fun Activity.setTransparentStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}