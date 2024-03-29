package com.company.ait.umc_flo.ui.main.locker

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.company.ait.umc_flo.ui.song.MusicFileFragment
import com.company.ait.umc_flo.ui.song.SavedSongFragment
import com.company.ait.umc_flo.ui.main.album.SavedAlbumFragment

class LockerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SavedSongFragment()
            1 -> MusicFileFragment()
            else -> SavedAlbumFragment()
        }
    }
}