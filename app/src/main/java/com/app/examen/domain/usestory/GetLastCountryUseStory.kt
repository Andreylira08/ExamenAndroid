package com.app.examen.domain.usestory


import com.app.examen.domain.repository.CountryRepository
import javax.inject.Inject


//Este UseStory es para que el viewModel No acceda directamente al repositorio
class GetLastCountryUseStory @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(): String? {
        return repository.getLastCountry()
    }
}