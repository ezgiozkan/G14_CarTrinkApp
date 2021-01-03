package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.car_add_page.*

class CarEditPagerActivity : AppCompatActivity(){

      private var carList: ArrayList<Car>? = null
      private var mSerializer : JsonSerializer? =null

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_fragment)

        val editbrandName = findViewById(R.id.brandName) as EditText
        val editmodelName = findViewById(R.id.modelName) as EditText
        val editfuelType = findViewById(R.id.fuelType) as EditText
        val editgearType = findViewById(R.id.gearType) as EditText
        val editprice = findViewById(R.id.price) as EditText
        val editkilometer = findViewById(R.id.kilometer) as EditText
        val editcolor = findViewById(R.id.color) as EditText
        val editdescription = findViewById(R.id.description) as EditText
        val done = findViewById(R.id.btnOk) as Button




        mSerializer = JsonSerializer("CarTrinkApp.json",
                applicationContext)

        try {
            carList = mSerializer!!.load()
        } catch (e: Exception) {
            carList = ArrayList()
            Log.e("Error loading cars: ", "", e)
        }
        // Previously entered data is being updated.
        btnOk.setOnClickListener {
            val updatedCarObj = Car()

            updatedCarObj.brandName = brandName.text.toString()
            updatedCarObj.modelName = modelName.text.toString()
            updatedCarObj.price = price.text.toString().toDouble()
            updatedCarObj.color = color.text.toString()
            updatedCarObj.fuelType = fuelType.text.toString()
            updatedCarObj.gearType = gearType.text.toString()
            updatedCarObj.km = kilometer.text.toString().toInt()
            updatedCarObj.description = description.text.toString()

            var jsonvar = updatedCarObj.updateJson(updatedCarObj)
            // Back the Recycler page
            MainActivity().onBackPressed()


        }
    }
}