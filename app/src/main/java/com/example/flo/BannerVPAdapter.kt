package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

//어댑터 클래스는 fragmentStateAdapter 클래스 상속 받아야 함
class BannerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    //리스트를 만들어서 여러 개의 fragment 담기
    //private를 해주지 않으면 해당 클래스에서만 사용해야 하는 변수가 다른 클래스에서도 접근할 수 있음 -> private 써줘야 외부에서 접근이 불가능하다
    private val fragmentList : ArrayList<Fragment> = ArrayList()

    //갯수 리턴
    override fun getItemCount(): Int  = fragmentList.size

    //0부터 getItemCount 값 반환
    override fun createFragment(position: Int): Fragment = fragmentList[position] //0, 1, 2, 3

    //함수가 처음 실행될 때 fragment리스트에 아무것도 업음 -> homefragment에서 추가해줄 fragment를 선택할 때 사용
    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
        notifyItemInserted(fragmentList.size-1)
    }
}