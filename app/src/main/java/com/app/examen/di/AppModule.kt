package com.app.examen.di

import com.app.examen.data.remote.api.CountryApi
import com.app.examen.data.repository.CountryRepositoryImpl
import com.app.examen.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Utilizamos module para poder realizar la configuración necesaria con la API

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryApi(retrofit: Retrofit): CountryApi {
        return retrofit.create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(
        api: CountryApi
    ): CountryRepository {
        return CountryRepositoryImpl(api)
    }
}

// Si alguien necesita un CountryRepository:

// Hilt ve que hay un @Provides para CountryRepository
// Pero necesita un CountryApi para crearlo
//
// Entonces busca cómo crear CountryApi
// Encuentra provideCountryApi
// Pero necesita un Retrofit
//
// Busca cómo crear Retrofit
// Encuentra provideRetrofit
// Este no necesita nada más
//
// Ahora puede:
// Crear Retrofit
// Usar ese Retrofit para crear CountryApi
// Usar ese CountryApi para crear CountryRepository
// Inyectar ese CountryRepository en MyViewModel
//
// Es como una receta:
//
// Para hacer un pastel (CountryRepository)
// Necesito masa (CountryApi)
// Para hacer masa necesito harina (Retrofit)
// Hilt sigue la receta al revés hasta tener todos los ingredientes
