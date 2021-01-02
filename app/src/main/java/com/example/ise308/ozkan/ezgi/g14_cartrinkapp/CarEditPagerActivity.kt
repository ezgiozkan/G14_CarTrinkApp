package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CarEditPagerActivity : AppCompatActivity(){

    private var carList: ArrayList<Car>? = null
    private var mSerializer : JsonSerializer? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.car_add_page)

        val brandName = findViewById(R.id.brandName) as EditText

        mSerializer = JsonSerializer("CarTrinkApp.json",
                applicationContext)

        try {
            carList = mSerializer!!.load()
        } catch (e: Exception) {
            carList = ArrayList()
            Log.e("Error loading cars: ", "", e)
        }

        val btnOk = findViewById(R.id.btnOk) as Button

        btnOk.setOnClickListener {

            for (car in carList!!){

                car.brandName = brandName.text.toString()


            }

        }

    }
}