package com.app.examen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryListDto (
    @SerializedName("results") val results: List<CountryDto>
)