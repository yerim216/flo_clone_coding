package com.example.flo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity: AppCompatActivity(){
    lateinit var binding : ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var title : String? = null
        var singer : String? = null

        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            title = intent.getStringExtra("title")
            singer = intent.getStringExtra("singer")
            binding.songTitleTv.text = title
            binding.songArtistNameTv.text = title

        }
        binding.songDownIb.setOnClickListener {
            //현재 액티비티 꺼주는 함수
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("message", title + " _ " + singer)
            setResult(RESULT_OK, intent)
            finish()

        }
        binding.songPlayIb.setOnClickListener {
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(true)
        }
        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.songTitleTv.text = intent.getStringExtra("title")
            binding.songArtistNameTv.text = intent.getStringExtra("singer")
        }
    }
        fun setPlayerStatus(isPlaying : Boolean){
            if(isPlaying){
                binding.songPlayIb.visibility = View.VISIBLE
                binding.songPauseIv.visibility = View.GONE
            }
            else {
                binding.songPlayIb.visibility = View.GONE
                binding.songPauseIv.visibility = View.VISIBLE
            }
        }




}

