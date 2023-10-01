package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentAlbumBinding

//fragment는 fragment 기능을 사용할 수 있게 해주는 fragment 클래스를 상속받음
class AlbumFragment : Fragment() {
    lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater, container, false)

        val bundle = arguments
        val imageResourceId = bundle?.getInt("imageId", 0)

        if (imageResourceId != null && imageResourceId!= 0) {
            view?.findViewById<ImageView>(R.id.album_image_iv)!!.setImageResource(imageResourceId)
        }

        binding.albumBackIb.setOnClickListener {
            val homeFragment = HomeFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, homeFragment)
                .commitAllowingStateLoss()
        }

        binding.linearLayoutSonglist.setOnClickListener {
            Toast.makeText(activity, "LILAC", Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }
}
