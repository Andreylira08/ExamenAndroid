package com.app.examen.data.local.model

import com.app.examen.domain.model.Country


//Este modelo será una similitud del que tiene la llamada
// a internet en el API, pero haremos nuestra propia estructura para facilitar el manejo que tendremos local.
data class CountryCache(
    val countryList: List<Country>,
    val lastUpdate: Long,
    val totalCount: Int
)