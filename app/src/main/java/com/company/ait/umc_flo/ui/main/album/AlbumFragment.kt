package com.company.ait.umc_flo.ui.main.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.company.ait.umc_flo.ui.main.home.HomeFragment
import com.company.ait.umc_flo.R
import com.company.ait.umc_flo.ui.song.SongDatabase
import com.company.ait.umc_flo.data.entities.Album
import com.company.ait.umc_flo.data.entities.Like
import com.company.ait.umc_flo.databinding.FragmentAlbumBinding
import com.company.ait.umc_flo.ui.main.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class AlbumFragment : Fragment() {

    lateinit var binding : FragmentAlbumBinding
    private var gson: Gson = Gson()

    private val information = arrayListOf("수록곡", "상세정보", "영상")

    private var isLiked : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        //Home에서 넘어온 데이터 받아오기
        val albumJson = arguments?.getString("album")
        val album = gson.fromJson(albumJson, Album::class.java)
        //Home에서 넘어온 데이터를 반영
        isLiked = isLikedAlbum(album.id)
        setInit(album)
        setOnClickListeners(album)

        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        val albumAdapter = AlbumVPAdapter(this)
        binding.albumContextVp.adapter = albumAdapter
        TabLayoutMediator(binding.albumContentTb, binding.albumContextVp) {
            tab, position ->
            tab.text = information[position]
        }.attach()

        return binding.root
    }

    private fun setInit(album: Album) {
        binding.albumAlbumIv.setImageResource(album.coverImg!!)
        binding.albumMusicTitleTv.text = album.title.toString()
        binding.albumSingerNameTv.text = album.singer.toString()
        if(isLiked) {
            binding.albumLikeIv.setImageResource(R.drawable.ic_my_like_on)
        } else {
            binding. albumLikeIv.setImageResource(R.drawable.ic_my_like_off)
        }
    }

    private fun getJwt() : Int {
        val spf = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        return spf!!.getInt("jwt",0)
    }

    private fun likeAlbum(userId: Int, albumId: Int) {
        val songDB = SongDatabase.getInstance(requireContext())!!
        val like = Like(userId, albumId)

        songDB.AlbumDao().likeAlbum(like)
    }

    private fun isLikedAlbum(albumId: Int):Boolean {
        val songDB = SongDatabase.getInstance(requireContext())!!
        val userId = getJwt()

        val likeId : Int? = songDB.AlbumDao().isLikedAlbum(userId, albumId)

        return likeId != null
    }

    private fun disLikedAlbum(albumId: Int) {
        val songDB = SongDatabase.getInstance(requireContext())!!
        val userId = getJwt()

       songDB.AlbumDao().disLikedAlbum(userId, albumId)
    }

    private fun setOnClickListeners(album: Album) {
        val userId = getJwt()
        binding.albumLikeIv.setOnClickListener {
            if(isLiked) {
                binding.albumLikeIv.setImageResource(R.drawable.ic_my_like_off)
                disLikedAlbum(album.id)
            } else {
                binding.albumLikeIv.setImageResource(R.drawable.ic_my_like_on)
                likeAlbum(userId, album.id)
            }
        }
    }
}