package com.klikdigital.pokelab.ui.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jaeger.library.StatusBarUtil
import com.klikdigital.pokelab.R
import com.klikdigital.pokelab.core.data.Resource
import com.klikdigital.pokelab.databinding.ActivityDetailBinding
import com.klikdigital.pokelab.ui.adapter.PokemonTypeAdapter
import com.klikdigital.pokelab.ui.adapter.SectionsPagerAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import com.klikdigital.pokelab.core.utils.Helper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.scope.scope

@DelicateCoroutinesApi
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
        StatusBarUtil.setLightMode(this)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2? = binding?.viewPager
        viewPager?.adapter = sectionsPagerAdapter

        val tabs: TabLayout? = binding?.tabs
        if (tabs != null && viewPager != null) {
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        // get id from ItemView RecyclerView List Pokemon
        val id = intent.getStringExtra(EXTRA_ID)
        if (id != null) {
            pokemonDetail(id)
        }

        // Back Pressed
        binding?.icBack?.setOnClickListener {
            onBackPressed()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
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

                            Glide.with(this)
                                .asBitmap()
                                .load(dataDetail[data].sprites.pokeImage)
                                .into(object : CustomTarget<Bitmap>() {
                                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                        binding?.ivPokemon?.setImageBitmap(resource)

                                        val vibrant = Helper.createPaletteSync(resource).lightVibrantSwatch
                                        vibrant?.rgb?.let { binding?.actDetail?.setBackgroundColor(it) }
                                    }

                                    override fun onLoadCleared(placeholder: Drawable?) {}
                                })
                            val dataArray = dataDetail[data].types
                            pokemonTypeAdapter.setData(dataArray)
                            pokemonTypeAdapter.notifyDataSetChanged()
                        }
                    }
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.tvHeight?.visibility = View.GONE
                        binding?.tvWeight?.visibility = View.GONE
                        binding?.viewLine?.visibility = View.GONE
                        binding?.ivEmptyState?.visibility = View.VISIBLE
                        binding?.tvEmptyStateTitle?.visibility = View.VISIBLE
                        binding?.tvEmptyStateDecs?.visibility = View.VISIBLE
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