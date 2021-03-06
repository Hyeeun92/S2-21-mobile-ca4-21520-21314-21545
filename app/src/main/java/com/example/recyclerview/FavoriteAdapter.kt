package com.example.recyclerview

import android.content.Intent
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class FavoriteAdapter(val stations: List<bikeStation>): RecyclerView.Adapter<FavoriteAdapter.CustomViewHolder>() {

    //Initialize check box boolean array
    val checkBoxStateArray = SparseBooleanArray()

    //count data for recyclerview list
    override fun getItemCount(): Int {
        return stations.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.CustomViewHolder, position: Int) {

        // get data from data class (from json)
        val bikestation = stations.get(position)

        holder?.view?.address?.text = bikestation.address
        holder?.view?.countryName?.text = bikestation.contract_name
        holder?.view?.abikeStand?.text = bikestation.available_bike_stands
        holder?.view?.abike?.text = bikestation.available_bikes

        holder?.bikestation = bikestation

        //set all check box is clicked
        holder.favCheck.isChecked = checkBoxStateArray.get(position, true)

    }

    //set keys for intent
    companion object {
        val CITY_KEY = "CITY"
        val ADDRESS_KEY = "ADDRESS"
        val POSITION_LAT_KEY = "POSITION_LAT"
        val POSITION_LNG_KEY = "POSITION_LNG"
        val ABIKESTAND = "AVAILABLE_BIKE_STNADS"
        val ABIKE = "AVAILABLE_BIKES"
    }

    inner class CustomViewHolder(val view: View, var bikestation: bikeStation? = null): RecyclerView.ViewHolder(view) {

        //initialize checkbox
        var favCheck = itemView.favCheck

        init {
            view.setOnClickListener {

                //use intent to send data to favorite station detail class
                val intent = Intent(view.context, FavoriteStationDetail::class.java)
                intent.putExtra(CITY_KEY, bikestation?.contract_name)
                intent.putExtra(ADDRESS_KEY, bikestation?.address)
                intent.putExtra(ABIKESTAND, bikestation?.available_bike_stands)
                intent.putExtra(ABIKE, bikestation?.available_bikes)
                intent.putExtra(POSITION_LAT_KEY, bikestation?.position?.lat)
                intent.putExtra(POSITION_LNG_KEY, bikestation?.position?.lng)
                view.context.startActivity(intent)
            }

            if (!checkBoxStateArray.get(adapterPosition, false)) {
                favCheck.isChecked = true
                checkBoxStateArray.put(adapterPosition, true)


            }else {
                favCheck.isChecked = false
                checkBoxStateArray.put(adapterPosition, false)






            }

        }

    }
}





