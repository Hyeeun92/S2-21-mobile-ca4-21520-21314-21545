package com.example.recyclerview

import android.content.Intent
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class FavoriteAdapter(val favList: List<bikeStation>): RecyclerView.Adapter<FavoriteAdapter.CustomViewHolder>() {

    val checkBoxStateArray = SparseBooleanArray()

    override fun getItemCount(): Int {
        return favList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val favlistItem = favList.get(position)
        holder?.view?.address?.text = favlistItem.contract_name
        holder?.view?.countryName?.text = favlistItem.address
        holder?.view?.abikeStand?.text = favlistItem.available_bike_stands
        holder?.view?.abike?.text = favlistItem.available_bikes

        holder?.favLists = favlistItem

        holder.favCheck.isChecked = checkBoxStateArray.get(position, true)
    }

    companion object {
        val CITY_KEY = "CITY"
        val ADDRESS_KEY = "ADDRESS"
        val POSITION_LAT_KEY = "POSITION_LAT"
        val POSITION_LNG_KEY = "POSITION_LNG"
        val ABIKESTAND = "AVAILABLE_BIKE_STNADS"
        val ABIKE = "AVAILABLE_BIKES"
    }

    inner class CustomViewHolder(val view: View, var favLists: bikeStation? = null): RecyclerView.ViewHolder(view) {

        var favCheck = itemView.favCheck

        init {
            view.setOnClickListener {
                    val intent = Intent(view.context, FavoriteStationDetail::class.java)

                    intent.putExtra(CITY_KEY, favLists?.contract_name)
                    intent.putExtra(ADDRESS_KEY, favLists?.address)
                    intent.putExtra(ABIKESTAND, favLists?.available_bike_stands)
                    intent.putExtra(ABIKE, favLists?.available_bikes)

                    intent.putExtra(POSITION_LAT_KEY, favLists?.position?.lat)
                    intent.putExtra(POSITION_LNG_KEY, favLists?.position?.lng)

                    view.context.startActivity(intent)
                }

            }

        }
}





