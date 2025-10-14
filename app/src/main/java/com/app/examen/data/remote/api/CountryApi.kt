package com.app.examen.data.remote.api

import com.app.examen.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("v3.1/all?fields=name,capital,currencies,continents,flags")
    suspend fun getCountryList(): List<CountryDto>

    @GET("v3.1/name/{name}?fields=name,capital,currencies,continents,flags")
    suspend fun getCountry(
        @Path("name") name: String): List<CountryDto>
}
