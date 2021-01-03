package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(){


    private var adapter: CarAdapter? = null
    private var carList : ArrayList<Car>? = null
    private var recyclerView: RecyclerView? = null
    private var mSerializer: JsonSerializer? = null
    private  var showDividers: Boolean = false



    // Our custom tool bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    // Calling mSerializer Load function for getting data.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSerializer = JsonSerializer("CarTrinkApp.json",
                applicationContext)


        try {
            carList = mSerializer!!.load()
        } catch (e: Exception) {
            carList = ArrayList()
            Log.e("Error loading cars: ", "", e)
        }
        // We get our Recycler View from xml file.
        recyclerView = findViewById<View>(R.id.recylerView) as RecyclerView

        adapter = CarAdapter(this, this.carList!!)


        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        // set the adapter
        recyclerView!!.adapter = adapter

    }

    // Create new object
    fun createNewCar(c: Car){

        carList!!.add(c)
        adapter!!.notifyDataSetChanged()

    }
    // Delete the object
    fun deleteCarSecond(car:Car){

        carList!!.remove(car)
        adapter!!.notifyItemRemoved(0)
        adapter!!.notifyDataSetChanged()
        mSerializer!!.save(carList!!)

    }

    // Saving object.
    private fun saveCar() {
        try {
            mSerializer!!.save(this.carList!!)
        } catch (e: Exception) {
            Log.e("Error Saving Cars", "", e)
        }
    }

    // Navigate to car_add_page layout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.OptionItemSelected -> {

                val dialog = NewCarPage()
                dialog.show(supportFragmentManager, "")

                true

            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences("Car Trink App ", Context.MODE_PRIVATE)

        showDividers = prefs.getBoolean("dividers", true)

        if (showDividers)
            recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        else{
            if (recyclerView!!.itemDecorationCount > 0)
                recyclerView!!.removeItemDecorationAt(0)
        }

    }
    // Calling again save function.
    override fun onPause() {
        super.onPause()
        saveCar()

    }



}