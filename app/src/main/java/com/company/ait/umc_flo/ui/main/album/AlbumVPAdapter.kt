package com.company.ait.umc_flo.ui.main.album

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.company.ait.umc_flo.ui.main.detail.DetailFragment
import com.company.ait.umc_flo.ui.song.SongFragment
import com.company.ait.umc_flo.ui.main.video.VideoFragment

class AlbumVPAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SongFragment()
            1 -> DetailFragment()
            else -> VideoFragment()
        }
    }
}