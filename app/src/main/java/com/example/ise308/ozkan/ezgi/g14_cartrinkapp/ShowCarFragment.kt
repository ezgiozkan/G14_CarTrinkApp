package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ShowCarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.car_frame, container, false)
        val tvBrandName = view.findViewById<TextView>(R.id.carBrandName)
        val tvModelName = view.findViewById<TextView>(R.id.carModelName)
        val tvPrice = view.findViewById<TextView>(R.id.carPrice)
        val tvColor = view.findViewById<TextView>(R.id.carColor)
        val tvFuelType = view.findViewById<TextView>(R.id.carFuelType)
        val tvGearType = view.findViewById<TextView>(R.id.carGearType)
        val tvKilometer = view.findViewById<TextView>(R.id.carKilometer)
        val tvDescription = view.findViewById<TextView>(R.id.carDescription)
        val tvImage = view.findViewById<ImageView>(R.id.imageView2)

        tvBrandName.text = arguments!!.getString("brandName") + "/" + arguments!!.getString("modelName")
        tvFuelType.text = arguments!!.getString("fuelType")
        tvGearType.text = arguments!!.getString("gearType")
        tvColor.text = arguments!!.getString("color")
        tvDescription.text = arguments!!.getString("description")
        tvPrice.text = arguments!!.getString("price")
        tvKilometer.text = arguments!!.getString("kilometer")
        // tvImage.setImageURI() = arguments!!.getString("image")

        val backButton = view.findViewById<ImageButton>(R.id.backButton)

        backButton.setOnClickListener {

            getActivity()?.getSupportFragmentManager()?.popBackStack();
        }



        return view
    }

    companion object{
        fun newInstance(car: Car) : ShowCarFragment {
            val fragment = ShowCarFragment()
            val bundle = Bundle(1)
            bundle.putString("modelName", car.modelName)
            bundle.putString("brandName", car.brandName)
            bundle.putString("price", car.price.toString())
            bundle.putString("kilometer", car.km.toString())
            bundle.putString("color", car.color)
            bundle.putString("fuelType", car.fuelType)
            bundle.putString("gearType", car.gearType)
            bundle.putString("description", car.description)
            bundle.putString("image", car.image.toString())
            fragment.arguments = bundle
            return fragment
        }
    }
}