package com.app.examen.data.mapper

import com.app.examen.data.remote.dto.CountryDto
import com.app.examen.domain.model.Country

fun CountryDto.toDomain(): Country {
    val nativeNameValue = name.nativeName?.values?.firstOrNull()?.official ?: "N/A"
    val coinValue = currencies.name
    val capitalValue = capital?.firstOrNull() ?: "N/A"
    val continentValue = continents?.firstOrNull() ?: "N/A"

    return Country(
        common = name.common,
        official = name.official,
        nativeName = nativeNameValue,
        coin = coinValue,
        capital = capitalValue,
        continent = continentValue,
        imageUrl = flags.png
    )
}