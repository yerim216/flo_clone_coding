package com.example.flo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

            var albumFragment = AlbumFragment()
            var bundle = Bundle()
           // val imageId = R.drawable.img_album_exp
            //Log.d("미리","${imageId}")
            //today_song_album_iv를 albumFragment로 전달
//            Drawble drawble = getResources().getDrawble(
//                R.drawable.img_album_exp
//            )
//            bundle.putInt("imageId",imageId )
//            albumFragment.arguments = bundle

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.main_frm, albumFragment)
                .commit()

        //리스트 안에 fragment 추가
        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter = bannerAdapter
        //뷰페이저가 좌우로 스크롤 될 수 있도록 지정
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        
        return binding.root
    }


}