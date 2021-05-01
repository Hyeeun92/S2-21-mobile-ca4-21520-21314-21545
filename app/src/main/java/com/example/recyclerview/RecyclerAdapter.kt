package com.example.recyclerview

import android.content.Intent
import android.renderscript.Sampler
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

    val checkBoxStateArray = SparseBooleanArray()
    var gson = GsonBuilder().create()
    var listType : TypeToken<MutableList<favList>> = object : TypeToken<MutableList<favList>>() {}
    val list_1: MutableList<favList> = mutableListOf()


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

        holder.favCheck.isChecked = checkBoxStateArray.get(position, false)

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

        var favCheck = itemView.favCheck

        init {
            view.setOnClickListener {
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

                    val address = bikestation?.address.toString()

                    list_1.add(favList(address))

                    var string_1 = gson.toJson(list_1,listType.type)
                    App.prefs.setS("1", string_1)
                    println(string_1)

                }else {
                    favCheck.isChecked = false
                    checkBoxStateArray.put(adapterPosition, false)

                    val address = bikestation?.address.toString()

                    list_1.remove(favList(address))

                    var string_1 = gson.toJson(list_1,listType.type)
                    App.prefs.setS("1", string_1)
                    println(string_1)


                }

                }


            }

        }

    }







