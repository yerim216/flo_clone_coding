package com.example.flo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity: AppCompatActivity(){
    //소괄호 : 클래스를 다른 클래스로 상속을 진행할 때는 소괄호를 넣어줘야 한다

    //전역변수
    lateinit var binding : ActivitySongBinding
    lateinit var song : Song
    lateinit var timer : Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //song 데이터 받아옴
        initSong()
        setPlayer(song)


//        binding.songDownIb.setOnClickListener {
//            //현재 액티비티 꺼주는 함수
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("message", title + " _ " + singer)
//            setResult(RESULT_OK, intent)
//            finish()
//
//        }
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

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }

    private fun initSong(){
        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 0),
                intent.getBooleanExtra("isPlaying", false)
            )
        }
        startTimer()
    }

    private fun setPlayer(song : Song){
        binding.songTitleTv.text = intent.getStringExtra("title")!!
        binding.songArtistNameTv.text = intent.getStringExtra("singer")!!
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second/60, song.second%60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime/60, song.playTime%60)
        binding.songProgressSb.progress = (song.second * 1000 /song.playTime)

        setPlayerStatus(song.isPlaying)


    }

        fun setPlayerStatus(isPlaying : Boolean){
            song.isPlaying = isPlaying
            timer.isPlaying = isPlaying

            if(isPlaying){
                binding.songPlayIb.visibility = View.VISIBLE
                binding.songPauseIv.visibility = View.GONE
            }
            else {
                binding.songPlayIb.visibility = View.GONE
                binding.songPauseIv.visibility = View.VISIBLE
            }
        }

    private fun startTimer(){
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }
    inner class Timer(private val playTime : Int, var isPlaying: Boolean = true):Thread(){
        private var second : Int = 0
        private var mills : Float = 0f

        override fun run() {
            super.run()
            try {
                while(true){

                    if(second >= playTime){
                        break
                    }

                    if(isPlaying){
                        sleep(50)
                        mills += 50

                        runOnUiThread{
                            binding.songProgressSb.progress = ((mills/playTime)*100).toInt()
                        }

                        if(mills % 1000 == 0f){
                            runOnUiThread{
                                binding.songStartTimeTv.text = String.format("%02d:%02d", second/60, second%60)
                            }
                            second ++
                        }

                    }
                }
            }catch (e:InterruptedException){
                Log.d("Song", "쓰레드가 죽었습니다. ${e.message}")
            }

        }
    }




}

