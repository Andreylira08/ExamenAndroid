package com.app.examen.domain.usestory

import com.app.examen.domain.repository.CountryRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.app.examen.domain.common.Result
import com.app.examen.domain.model.Country

class GetCountryListUseStory
@Inject
constructor(
    private val repository: CountryRepository,
) {
    operator fun invoke(): Flow<Result<List<Country>>> =
        flow {
            try {
                emit(Result.Loading) // Indicamos que estamos cargando los datos
                val countryList = repository.getCountryList() // Obtenemos los datos de la API
                emit(Result.Success(countryList)) // Indicamos que los datos se han obtenido correctamente
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}