package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

private const val TAG = "CarpagerActivity"
private var carList: ArrayList<Car>? = null
var mSerializer : JsonSerializer? = null

class CarPagerActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_pager)

        mSerializer = JsonSerializer("CarTrinkApp.json",
            applicationContext)

        try {
            carList = mSerializer!!.load()
        } catch (e: Exception) {
            carList = ArrayList()
            Log.e("Error loading cars: ", "", e)
        }

        // create list of fragments, one fragment for each car
        var carFragmentList = java.util.ArrayList<Fragment>()
        for (car in carList!!) {
            carFragmentList.add(ShowCarFragment.newInstance(car))

        }



        val pageAdapter = CarPagerAdapter(supportFragmentManager, carFragmentList)
        findViewById<ViewPager>(R.id.pager_cars).adapter = pageAdapter


    }
    class CarPagerAdapter(fm: FragmentManager, private val carFragmentList: ArrayList<Fragment>) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount() = carFragmentList.size
        override fun getItem(position: Int) = carFragmentList[position]

    }
}
