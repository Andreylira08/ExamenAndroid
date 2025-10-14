package com.app.examen.data.repository
import com.app.examen.data.mapper.toDomain
import com.app.examen.data.remote.api.CountryApi
import com.app.examen.domain.model.Country
import com.app.examen.domain.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi
) : CountryRepository {

    override suspend fun getCountryList(): List<Country> {

        val response = api.getCountryList()
        return response.map { it.toDomain() }
    }

    override suspend fun getCountryByName(name: String): Country {
        return api.getCountry(name).first().toDomain()
    }
}