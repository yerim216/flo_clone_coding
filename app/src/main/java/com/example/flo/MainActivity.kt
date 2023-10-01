package com.example.flo

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.flo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //val textView = finViewById<TextView>(R.id.main_player_cl) -> 변수와 activitymain에 있는 view와 연결
    //위 방식을 사용하면 많은 변수를 사용해야 함. nullPointerException 문제 발생할 수도 있음 -> binding 사용!
    //binding 사용해야 하는 이유 : 코드 가독성 높임, 널포인터문제 없앰
    lateinit var binding: ActivityMainBinding

    companion object {const val STRING_INTENT_KEY = "my_string_key"}
    //ActivityResult를 받기 위한 Callback 등록
    private val getResultText = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        result ->
        if(result.resultCode == Activity.RESULT_OK){
            val returnString = result.data?.getStringExtra(STRING_INTENT_KEY)
            Toast.makeText(this, returnString, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val song = Song(binding.mainMiniPlayerTitleTv.text.toString(), binding.mainMiniPlayerSingerTv.text.toString())
        Log.d("Song", song.title + song.singer)

        //intent : activity에서 사용하는 택배 상자 -> data를 담아서 보냄
        binding.mainPlayerCl.setOnClickListener{
            //startActivity(Intent(this, SongActivity::class.java))
            val intent = Intent(this,SongActivity::class.java)
            intent.putExtra("title", song.title)
            intent.putExtra("singer", song.singer)
            startActivity(intent)
        }


        initBottomNavigation()


    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}