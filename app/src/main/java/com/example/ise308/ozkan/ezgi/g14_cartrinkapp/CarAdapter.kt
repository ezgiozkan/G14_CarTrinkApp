package com.example.ise308.ozkan.ezgi.g14_cartrinkapp

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CarAdapter(

    private val mainActivity: MainActivity,
    private val carList: ArrayList<Car>)

    : RecyclerView.Adapter<CarAdapter.ListItemHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): ListItemHolder {
        // We assign our car_list_page xml
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.car_list_page, parent, false)

        return ListItemHolder(itemView)

    }
    inner class ListItemHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnLongClickListener,
        View.OnClickListener {
        // We handle our UI component from Layout file.
        internal var brandName = view.findViewById<TextView>(R.id.listBrandName)

        internal var modelName = view.findViewById<TextView>(R.id.listModelName)

        internal var price = view.findViewById<TextView>(R.id.listPrice)

        internal var kilometer = view.findViewById<TextView>(R.id.listKilometer)

        internal var img = view.findViewById<ImageView>(R.id.img)

        init {
            view.isClickable = true
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        // We get the current position and removed from carlist array. Then saving JsonSerializer.
        override fun onLongClick(v: View?): Boolean {

            val position = adapterPosition
            carList!!.removeAt(position)
            notifyItemRemoved(position)
            mSerializer?.save(carList!!)
            return true
        }

        // When we click holder item, go to activity_car_pager layout.
        override fun onClick(view: View) {

            val intentToCarPager = Intent(view!!.context, CarPagerActivity::class.java)
            intentToCarPager.putExtra("adapterPosition",adapterPosition)
            view.context.startActivity(intentToCarPager)

        }

    }
    // we show data using this function for the recycler.
    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {

        val car = carList!![position]

        holder.brandName.text = car.brandName.toString()

        holder.modelName.text = car.modelName.toString()

        holder.price.text = car.price.toString()

        holder.kilometer.text = car.km.toString()

        holder.img.setImageURI(car.image)


    }
    // Our car arrays size.
    override fun getItemCount(): Int {
        if (carList != null) {
            return carList.size
        }
        return -1
    }



}
