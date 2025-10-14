package com.app.examen.data.remote.api

import com.app.examen.data.remote.dto.CountryDto
import com.app.examen.data.remote.dto.CountryListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("v3.1/all")
    suspend fun getCountryList(): List<CountryDto>

    @GET("v3.1/name/{name}")
    suspend fun getCountry(
        @Path("name") name: String): List<CountryDto>
}
