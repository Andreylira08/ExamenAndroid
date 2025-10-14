package com.app.examen.domain.repository
import com.app.examen.domain.model.Country

// Recolectamos aqui la información de la API
// NO LE IMPORTA DE DONDE VIENEN LOS DATOS

interface CountryRepository {
    suspend fun getCountryList(): List<Country>
    suspend fun getCountryByName(name: String): Country
}
