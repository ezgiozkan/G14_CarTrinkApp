package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class NewCarPage : DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater

        val dialogView = inflater.inflate(R.layout.car_add_page, null)

        val brandName = dialogView.findViewById(R.id.brandName) as EditText

        val modelName = dialogView.findViewById(R.id.modelName) as EditText

        val fuelType = dialogView.findViewById(R.id.fuelType) as EditText

        val gearType = dialogView.findViewById(R.id.gearType) as EditText

        val price = dialogView.findViewById(R.id.price) as EditText

        val kilometer = dialogView.findViewById(R.id.kilometer) as EditText

        val color = dialogView.findViewById(R.id.color) as EditText

        val description = dialogView.findViewById(R.id.description) as EditText


        builder.setView(dialogView)
        //user can add a new car for tap done button

        val btnOk = dialogView.findViewById(R.id.btnOk) as Button


        btnOk.setOnClickListener() {


            var newCar = Car()



            newCar.brandName = brandName.text.toString()
            newCar.modelName = modelName.text.toString()

            newCar.price = price.text.toString().toDouble()

            newCar.fuelType = fuelType.text.toString()
            newCar.gearType = gearType.text.toString()
            newCar.km = kilometer.text.toString().toDouble()


            val callingActivity = activity as MainActivity?

            // Pass newCar back to MainActivity!!


            dismiss()

        }

        return builder.create()


    }

}