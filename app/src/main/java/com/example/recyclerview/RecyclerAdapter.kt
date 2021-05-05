package com.example.recyclerview

/*
21545 - Hyeeun Lee
21520 - Liubov Eremenko
21314 - Nathalie Flores
*/

import android.content.Intent
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.recycler_row.view.*

data class favList(val address:String)

class RecyclerAdapter(val stations: List<bikeStation>): RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {

    //Initialize check box boolean array
    val checkBoxStateArray = SparseBooleanArray()
    //Initialize gson builder to save favorite list
    var gson = GsonBuilder().create()
    //Initialize list for favorite list to save as json
    var listType : TypeToken<MutableList<favList>> = object : TypeToken<MutableList<favList>>() {}
    val list_1: MutableList<favList> = mutableListOf()

    //count data for recyclerview list
    override fun getItemCount(): Int {
        return stations.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        // get data from data class (from json)
        val bikestation = stations.get(position)

        holder?.view?.address?.text = bikestation.address
        holder?.view?.countryName?.text = bikestation.contract_name
        holder?.view?.abikeStand?.text = bikestation.available_bike_stands
        holder?.view?.abike?.text = bikestation.available_bikes

        holder?.bikestation = bikestation

        //set all check box is not clicked
        holder.favCheck.isChecked = checkBoxStateArray.get(position, false)

    }

    //set keys for intent
    companion object {
        val CITY_KEY = "CITY"
        val ADDRESS_KEY = "ADDRESS"
        val POSITION_LAT_KEY = "POSITION_LAT"
        val POSITION_LNG_KEY = "POSITION_LNG"
        val ABIKESTAND = "AVAILABLE_BIKE_STNADS"
        val ABIKE = "AVAILABLE_BIKES"
        val POSITION = "POSITION"
    }

    inner class CustomViewHolder(val view: View, var bikestation: bikeStation? = null): RecyclerView.ViewHolder(view) {

        //initialize checkbox
        var favCheck = itemView.favCheck

        init {
            view.setOnClickListener {
                //use intent to send data to favorite station detail class
                val intent = Intent(view.context, StationDetail::class.java)
                intent.putExtra(CITY_KEY, bikestation?.contract_name)
                intent.putExtra(ADDRESS_KEY, bikestation?.address)
                intent.putExtra(ABIKESTAND, bikestation?.available_bike_stands)
                intent.putExtra(ABIKE, bikestation?.available_bikes)
                intent.putExtra(POSITION_LAT_KEY, bikestation?.position?.lat)
                intent.putExtra(POSITION_LNG_KEY, bikestation?.position?.lng)
                view.context.startActivity(intent)
            }
            favCheck.setOnClickListener {
                if (!checkBoxStateArray.get(adapterPosition, false)) {
                    favCheck.isChecked = true
                    checkBoxStateArray.put(adapterPosition, true)
                    //Initialize if the check box clicked to save address
                    val address = bikestation?.address.toString()
                    //add to list
                    list_1.add(favList(address))
                    // make json
                    var string_1 = gson.toJson(list_1,listType.type)
                    //shared preferences set key = 1, value = address list
                    App.prefs.setS("1", string_1)
                }else {
                    favCheck.isChecked = false
                    checkBoxStateArray.put(adapterPosition, false)
                    //Initialize if the check box not clicked
                    val address = bikestation?.address.toString()
                    //remove the addree in the list
                    list_1.remove(favList(address))
                    //shared preferences set key = 1, value = address list
                    var string_1 = gson.toJson(list_1,listType.type)
                    App.prefs.setS("1", string_1)
                }
            }
        }
    }
}







