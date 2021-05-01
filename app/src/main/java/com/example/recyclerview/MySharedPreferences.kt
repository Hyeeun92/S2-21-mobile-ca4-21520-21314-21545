package com.example.recyclerview

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class favoriteList(val address: String?) : Parcelable

class MySharedPreferences (context: Context) {

    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_EDITTEXT = "address"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    fun getS(key: String): String?{

        return prefs.getString(key,"")
    }
    fun setS(key: String, value: String?) {

        prefs.edit().putString(key,value).apply()
    }

    var address: String?
        get() = prefs.getString(PREF_KEY_MY_EDITTEXT, "")
        set(value) = prefs.edit().putString(PREF_KEY_MY_EDITTEXT, value).apply()

}
