package com.example.ise308.ozkan.ezgi.g14_cartrink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ise308.ozkan.ezgi.g14_cartrinkapp.Car
import com.example.ise308.ozkan.ezgi.g14_cartrinkapp.NewCarPage
import com.example.ise308.ozkan.ezgi.g14_cartrinkapp.R
import com.example.ise308.ozkan.ezgi.g14_trinkcar.CarAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: CarAdapter? = null
    private val carList = ArrayList<Car>()
    private var recyclerView: RecyclerView? = null


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        addCarBtn.setOnClickListener { view->
            val dialog = NewCarPage()
            dialog.show(supportFragmentManager, "")
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.OptionItemSelected -> {

                val intent = Intent(this, NewCarPage::class.java)

                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    fun createNewCar(c: Car){

        carList.add(c)
        adapter!!.notifyDataSetChanged()
    }





}