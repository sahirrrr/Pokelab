package com.klikdigital.pokelab.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.klikdigital.pokelab.ui.stats.StatsFragment

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return StatsFragment.newInstance( position +1)
    }
}