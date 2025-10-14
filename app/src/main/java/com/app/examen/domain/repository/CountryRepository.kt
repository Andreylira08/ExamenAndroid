package com.app.examen.domain.repository
import com.app.examen.domain.model.Country

interface CountryRepository {
    suspend fun getCountryList(): List<Country>
    suspend fun getCountryByName(name: String): Country
}
