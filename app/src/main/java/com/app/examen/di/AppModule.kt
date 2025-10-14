package com.app.examen.di

import android.content.Context
import com.app.examen.data.local.preferences.CountryPreferences
import com.app.examen.data.remote.api.CountryApi
import com.app.examen.data.repository.CountryRepositoryImpl
import com.app.examen.domain.repository.CountryRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Utilizamos module para poder realizar la configuración necesaria con la API

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
fun provideGson(): Gson {
    return Gson()
}


    @Provides
    @Singleton
    fun provideCountryApi(retrofit: Retrofit): CountryApi {
        return retrofit.create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryPreferences(
        @ApplicationContext context: Context,
        gson: Gson
    ): CountryPreferences {
        return CountryPreferences(context, gson)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(
        api: CountryApi,
        preferences: CountryPreferences
    ): CountryRepository {
        return CountryRepositoryImpl(api, preferences)
    }
    }

