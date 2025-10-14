package com.app.examen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryDto(
    // @SerializedName le dice a Gson como mapear los datos de la API a nuestra clase de datos

    @SerializedName("name") val name: NameDto,

    @SerializedName("currencies") val currencies: Map<String, CurrencyDto>?,

    @SerializedName("capital") val capital: List<String>?,

    @SerializedName("continents") val continents: List<String>?,

    @SerializedName("flags") val flags: FlagDto

) {

    data class NameDto(
        @SerializedName("common") val common: String,

        @SerializedName("official") val official: String,

        @SerializedName("nativeName") val nativeName: Map<String, NativeNameDto>?    )

    data class NativeNameDto(
        @SerializedName("official") val official: String
    )

    data class CurrencyDto(
        @SerializedName("name") val name: String,
    )


    data class FlagDto(
        @SerializedName("png") val png: String,

    )
}
