package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShowCarFragment(var car: Car) : Fragment() {

    var isOpen = false


    @RequiresApi(Build.VERSION_CODES.KITKAT)
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
        val addButton = view.findViewById<FloatingActionButton>(R.id.addButton)
        val editButton = view.findViewById<FloatingActionButton>(R.id.editButton)
        val deleteButton = view.findViewById<FloatingActionButton>(R.id.deleteButton)


        tvBrandName.text = arguments!!.getString("brandName") + "/" + arguments!!.getString("modelName")
        tvFuelType.text = arguments!!.getString("fuelType")
        tvGearType.text = arguments!!.getString("gearType")
        tvColor.text = arguments!!.getString("color")
        tvDescription.text = arguments!!.getString("description")
        tvPrice.text = arguments!!.getString("price")
        tvKilometer.text = arguments!!.getString("kilometer")
        // tvImage.setImageURI() = arguments!!.getString("image")

        val fabOpen = AnimationUtils.loadAnimation(this.context,R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this.context,R.anim.fab_close)
        val fabRAntiClockwise = AnimationUtils.loadAnimation(this.context,R.anim.rotate_anticlockwise)
        val fabRClockwise = AnimationUtils.loadAnimation(this.context,R.anim.rotate_clockwise)

        println("51. satır")



        addButton.setOnClickListener() {



            if (isOpen) {

                editButton.startAnimation(fabClose)
                deleteButton.startAnimation(fabClose)
                addButton.startAnimation(fabRClockwise)
                println("60. satır")


                isOpen = false

            }else {

                editButton.startAnimation(fabOpen)
                deleteButton.startAnimation(fabOpen)
                addButton.startAnimation(fabRAntiClockwise)
                editButton.isClickable
                deleteButton.isClickable
                println("72. satır")
                isOpen = true

            }
            editButton.setOnClickListener()  {

                print("xx")
            }
            deleteButton.setOnClickListener()  {

                val mAlertDialog = AlertDialog.Builder(this.activity)

                mAlertDialog.setTitle("Are you sure you want to this advertising!") //set alertdialog title

                mAlertDialog.setPositiveButton("Yes") { dialog, id ->

                    fragmentManager?.beginTransaction()?.remove(this)?.commit()

                    val intent = Intent(this.context,MainActivity::class.java)

                    startActivity(intent)

                    (activity as MainActivity).deleteCar()
                    (activity as MainActivity).onBackPressed()


                }
                mAlertDialog.setNegativeButton("No") { dialog, id ->


                }
                mAlertDialog.show()

            }
        }






        return view
    }

    companion object{
        fun newInstance(car: Car) : ShowCarFragment {
            val fragment = ShowCarFragment(car)
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