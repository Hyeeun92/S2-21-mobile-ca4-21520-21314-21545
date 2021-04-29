package com.example.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class FavoriteAdapter(val favLists: ArrayList<favstation>): RecyclerView.Adapter<FavoriteAdapter.CustomViewHolder>() {

    override fun getItemCount(): Int {
        return favLists.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val favlistItem = favLists.get(position)
        holder?.view?.address?.text = favlistItem.stationAddress
        holder?.view?.countryName?.text = favlistItem.stationCity
        holder?.view?.abikeStand?.text = favlistItem.abikeStation
        holder?.view?.abike?.text = favlistItem.aBike

        holder?.favstation = favlistItem
    }

    companion object {
        val CITY_KEY = "CITY"
        val ADDRESS_KEY = "ADDRESS"
        val POSITION_LAT_KEY = "POSITION_LAT"
        val POSITION_LNG_KEY = "POSITION_LNG"
        val ABIKESTAND = "AVAILABLE_BIKE_STNADS"
        val ABIKE = "AVAILABLE_BIKES"
    }

    inner class CustomViewHolder(val view: View, var favstation: favstation? = null): RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                    val intent = Intent(view.context, FavoriteStationDetail::class.java)

                    intent.putExtra(CITY_KEY, favstation?.stationCity)
                    intent.putExtra(ADDRESS_KEY, favstation?.stationAddress)
                    intent.putExtra(ABIKESTAND, favstation?.abikeStation)
                    intent.putExtra(ABIKE, favstation?.aBike)

                    intent.putExtra(POSITION_LAT_KEY, favstation?.lat)
                    intent.putExtra(POSITION_LNG_KEY, favstation?.lng)

                    view.context.startActivity(intent)
                }

            }

        }
}





