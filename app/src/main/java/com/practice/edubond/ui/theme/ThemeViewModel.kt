package com.practice.edubond.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeDataStore: ThemeDataStore
) : ViewModel() {
    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode = _isDarkMode.asStateFlow()

    init {
        // ✅ Load saved theme on startup
        viewModelScope.launch {
            themeDataStore.getTheme().collect { savedTheme ->
                _isDarkMode.value = savedTheme
            }
        }
    }

    fun toggleTheme() {
        val newTheme = !_isDarkMode.value
        _isDarkMode.value = newTheme
        viewModelScope.launch {
            themeDataStore.saveTheme(newTheme)  // ✅ Save to DataStore
        }
    }
}
