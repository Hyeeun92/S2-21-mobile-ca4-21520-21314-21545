package com.example.recyclerview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val stations: Array<bikeStation>): RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {

    var checkBoxStateArray = SparseBooleanArray()

    override fun getItemCount(): Int {
        return stations.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val bikestation = stations.get(position)
        holder?.view?.address?.text = bikestation.address
        holder?.view?.countryName?.text = bikestation.contract_name
        holder?.view?.abikeStand?.text = bikestation.available_bike_stands
        holder?.view?.abike?.text = bikestation.available_bikes

        holder?.bikestation = bikestation

        holder.checkbox.isChecked = checkBoxStateArray.get(position, false)

    }
    companion object {
        val CITY_KEY = "CITY"
        val ADDRESS_KEY = "ADDRESS"
        val POSITION_LAT_KEY = "POSITION_LAT"
        val POSITION_LNG_KEY = "POSITION_LNG"
        val ABIKESTAND = "AVAILABLE_BIKE_STNADS"
        val ABIKE = "AVAILABLE_BIKES"
    }

    inner class CustomViewHolder(val view: View, var bikestation: bikeStation? = null): RecyclerView.ViewHolder(view) {

        val checkbox = itemView.checkBox

        init {
            view.setOnClickListener {

                val intent = Intent(view.context, StationDetail::class.java)

                intent.putExtra(CITY_KEY, bikestation?.contract_name)
                intent.putExtra(ADDRESS_KEY, bikestation?.address)
                intent.putExtra(ABIKESTAND, bikestation?.available_bike_stands)
                intent.putExtra(ABIKE, bikestation?.available_bikes)

                intent.putExtra(POSITION_LAT_KEY, bikestation?.position?.lat)
                intent.putExtra(POSITION_LNG_KEY, bikestation?.position?.lng)
                intent.putExtra(CITY_KEY, bikestation?.contract_name)

                view.context.startActivity(intent)
            }

            checkbox.setOnClickListener{
                if(!checkBoxStateArray.get(adapterPosition, false)) {
                    checkbox.isChecked = true
                    checkBoxStateArray.put(adapterPosition, true)

                    var checkedStationAddress = bikestation?.address
                    var checkedStationCity = bikestation?.contract_name
                    var checkedStationABikeStation = bikestation?.available_bike_stands
                    var checkedStationABike = bikestation?.available_bikes


                }
                else {
                    checkbox.isChecked = false
                    checkBoxStateArray.put(adapterPosition, false)
                }
            }
        }

    }


}

