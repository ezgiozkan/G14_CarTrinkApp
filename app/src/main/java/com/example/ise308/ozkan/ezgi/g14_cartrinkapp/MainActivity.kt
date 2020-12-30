package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: CarAdapter? = null
    private var carList : ArrayList<Car>? = null
    private var recyclerView: RecyclerView? = null
    private var mSerializer: JsonSerializer? = null
    private  var showDividers: Boolean = false



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


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

        recyclerView = findViewById<View>(R.id.recylerView) as RecyclerView

        adapter = CarAdapter(this, this.carList!!)

        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        /* // Add a neat dividing line between items in the list
         recyclerView!!.addItemDecoration(
             DividerItemDecoration(this,
                 LinearLayoutManager.VERTICAL)
         )*/

        // set the adapter
        recyclerView!!.adapter = adapter


    }

    fun createNewCar(c: Car){

        carList!!.add(c)
        adapter!!.notifyDataSetChanged()
    }
    fun showCar(carToShow: Int) {
        val dialog = ShowCarListPage()
        dialog.sendCarSelected(carList!![carToShow])
        dialog.show(supportFragmentManager, "")
    }

    private fun saveCar() {
        try {
            mSerializer!!.save(this.carList!!)
        } catch (e: Exception) {
            Log.e("Error Saving Cars", "", e)
        }
    }






    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.OptionItemSelected -> {

                //val intent = Intent(this, NewCarPage::class.java)

              //  startActivity(intent)
              //  true

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
    override fun onPause() {
        super.onPause()
        saveCar()
        Toast.makeText(this, "Cars saved to JSON file", Toast.LENGTH_SHORT).show()

    }


}