package com.app.examen.presentation.screens.home
import com.app.examen.domain.common.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examen.domain.usestory.GetCountryListUseStory
import com.app.examen.domain.usestory.GetLastCountryUseStory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCountryListUseStory: GetCountryListUseStory,
    private val getLastCountryUseStory: GetLastCountryUseStory
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<String?>(null)
    val navigationEvent: StateFlow<String?> = _navigationEvent.asStateFlow()

    init {
        loadCountryList()
        checkLastCountry()
    }

    //Con estafuncion mandamos a llamar cual es el ultimo país
    private fun checkLastCountry() {
        viewModelScope.launch {
            val lastCountry = getLastCountryUseStory()
            if (lastCountry != null) {
                _navigationEvent.value = lastCountry
            }
        }
    }

    fun onNavigationHandled() {
        _navigationEvent.value = null
    }

    private fun loadCountryList() {
        viewModelScope.launch {
            getCountryListUseStory().collect { result ->
                _uiState.update { state ->
                    when (result) {
                        is Result.Loading -> state.copy(
                            isLoading = true
                        )
                        is Result.Success -> state.copy(
                            countryList = result.data,
                            isLoading = false,
                            error = null
                        )
                        is Result.Error -> state.copy(
                            error = result.exception.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}