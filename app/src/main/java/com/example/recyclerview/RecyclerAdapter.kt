package com.example.recyclerview

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val stations: Array<bikeStation>): RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {

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

        init {
            view.setOnClickListener {
                if (bikestation?.contract_name == "dublin") {

                    val intent = Intent(view.context, StationDetailDublin::class.java)

                    intent.putExtra(CITY_KEY, bikestation?.contract_name)
                    intent.putExtra(ADDRESS_KEY, bikestation?.address)
                    intent.putExtra(ABIKESTAND, bikestation?.available_bike_stands)
                    intent.putExtra(ABIKE, bikestation?.available_bikes)

                    intent.putExtra(POSITION_LAT_KEY, bikestation?.position?.lat)
                    intent.putExtra(POSITION_LNG_KEY, bikestation?.position?.lng)

                    view.context.startActivity(intent)
                }
                else {
                    val intent = Intent(view.context, StationDetailParis::class.java)

                    intent.putExtra(CITY_KEY, bikestation?.contract_name)
                    intent.putExtra(ADDRESS_KEY, bikestation?.address)
                    intent.putExtra(ABIKESTAND, bikestation?.available_bike_stands)
                    intent.putExtra(ABIKE, bikestation?.available_bikes)

                    intent.putExtra(POSITION_LAT_KEY, bikestation?.position?.lat)
                    intent.putExtra(POSITION_LNG_KEY, bikestation?.position?.lng)
                    intent.putExtra(CITY_KEY, bikestation?.contract_name)

                    view.context.startActivity(intent)
                }
            }

        }

    }



}

