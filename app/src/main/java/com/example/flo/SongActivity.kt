package com.example.flo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding


//activity는 상속 받아야 하는 class가 있다
//kotlin에서는 자바(extends) 대신 : 사용
//AppCompatActivity : 안드로이드에서 액티비티 기능을 사용할 수 있도록 만든 클래스 , 소괄호 붙여줘야 함(코틀린 상속 시 문법)
//  build.gradle에 viewBinding {
//        enabled true
//    }
//추가 해줘야 함.
//binding -> 전역 변수에 선언 해줘야 함
//lateinit var : 자바의 전방 선언 . 나중에 초기화를 해줄게 하는 사용 방법

//kotlin 변수 선언법
//var : 처음 선언, 초기화 후 값 변경 가능
//val : 처음 선언 후 값 변경 불가능
// var test1 : String = 'dd'
// val test2 : Int = 1

//binding 사용 방법
//layout에 있는 xml 파일과 연결 해줘야 함 -> 카멜 케이스
class SongActivity: AppCompatActivity(){
    lateinit var binding : ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.songDownIb.setOnClickListener {
            //현재 액티비티 꺼주는 함수
            val intent = Intent(this, MainActivity::class.java).apply{
                putExtra(MainActivity.STRING_INTENT_KEY, binding.songTitleTv.text.toString() )
            }
            setResult(Activity.RESULT_OK, intent)
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

