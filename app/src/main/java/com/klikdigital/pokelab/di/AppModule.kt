package com.klikdigital.pokelab.di

import com.klikdigital.pokelab.domain.usecase.PokemonInteractor
import com.klikdigital.pokelab.domain.usecase.PokemonUseCase
import com.klikdigital.pokelab.ui.detail.DetailViewModel
import com.klikdigital.pokelab.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PokemonUseCase> { PokemonInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}