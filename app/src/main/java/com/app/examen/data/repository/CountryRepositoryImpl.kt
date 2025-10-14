package com.app.examen.data.repository
import com.app.examen.data.local.preferences.CountryPreferences
import com.app.examen.data.mapper.toDomain
import com.app.examen.data.remote.api.CountryApi
import com.app.examen.domain.model.Country
import com.app.examen.domain.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi,
    private val preferences: CountryPreferences, //CACHE
) : CountryRepository {

    override suspend fun getCountryList(): List<Country> {

        val response = api.getCountryList()
        return response.map { it.toDomain() }
    }

    override suspend fun getCountryByName(name: String): Country {
        val country = api.getCountry(name).first().toDomain()
        preferences.saveLastCountry(name) // Guardar el país visitado
        return country
    }

    override fun getLastCountry(): String? {
        return preferences.getLastCountry()
    }
}