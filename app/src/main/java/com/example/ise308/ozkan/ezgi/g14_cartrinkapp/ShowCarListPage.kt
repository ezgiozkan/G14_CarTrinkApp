package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.app.Dialog
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.car_frame.*

class ShowCarListPage: DialogFragment() {

    private var car: Car? = null




    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {



        val builder = AlertDialog.Builder(this.activity!!)

        val inflater = activity!!.layoutInflater

        val dialogView = inflater.inflate(R.layout.car_detail_page, null)

        val txtbrandName = dialogView.findViewById<TextView>(R.id.carBrandName)

        val txtmodelName = dialogView.findViewById<TextView>(R.id.carModelName)

        val txtPrice = dialogView.findViewById<TextView>(R.id.carPrice)

        val txtColor = dialogView.findViewById<TextView>(R.id.carColor)

        val txtFuelType = dialogView.findViewById<TextView>(R.id.carFuelType)

        val txtKilometer = dialogView.findViewById<TextView>(R.id.carKilometer)

        val txtGearType = dialogView.findViewById<TextView>(R.id.carGearType)

        val txtDescription = dialogView.findViewById<TextView>(R.id.carDescription)





        txtbrandName.text = car!!.brandName
        txtmodelName.text = car!!.modelName
        txtColor.text = car!!.color
        txtPrice.text = car!!.price.toString()
        txtKilometer.text = car!!.km.toString()
        txtFuelType.text = car!!.fuelType
        txtGearType.text = car!!.gearType
        txtDescription.text = car!!.description
        return builder.create()

    }

    fun sendCarSelected(carSelected: Car) {
        car = carSelected
    }

}