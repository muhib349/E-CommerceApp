package com.ee.ecommerce.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferenceProvider(private val context: Context) {

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun saveItem(key: String, value: String){
        preference.edit().putString(key,value).apply()
    }

    fun  getSavedItem(key: String): String? {
        return preference.getString(key, null)
    }
}