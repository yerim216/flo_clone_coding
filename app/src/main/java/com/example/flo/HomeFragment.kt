package com.example.flo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeTodaySongAlbumIv.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm,AlbumFragment()).commitAllowingStateLoss()
            //homefragment는 mainactivity에 있는 하나의 조각, 그 조각을 어디서 변경하는지 써줌

            var albumFragment = AlbumFragment()
            var bundle = Bundle()
            val imageId = R.id.home_today_song_album_iv
            bundle.putInt("imageId",imageId )
            albumFragment.arguments = bundle

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.album_image_iv, albumFragment)
                .commit()
        }



        
        return binding.root
    }


}