package com.app.examen.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.app.examen.data.local.model.CountryCache
import com.app.examen.domain.model.Country
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryPreferences
@Inject
constructor(
    @ApplicationContext context: Context,
    private val gson: Gson,
) {

    //SharedPreferences es un mecanismo de almacenamiento clave-valor en Android que
    // permite guardar datos primitivos de manera persistente.
    //Es como una pequeña base de datos simple.
    private val prefs: SharedPreferences =
        context.getSharedPreferences(
            PreferencesConstants.PREF_NAME,
            Context.MODE_PRIVATE,
        )


    //Guardado de datos
    fun saveLastCountry(countryName: String) {
        prefs
            .edit()
            .putString(PreferencesConstants.KEY_LAST_COUNTRY, countryName)
            .apply()
    }

    //Obtenemos los datos guardados
    fun getLastCountry(): String? {
        return prefs.getString(PreferencesConstants.KEY_LAST_COUNTRY, null)
    }

}