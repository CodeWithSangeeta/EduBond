package com.practice.edubond.ui.theme


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private const val THEME_PREFERENCES = "theme_preferences"

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(
    name = THEME_PREFERENCES
)

@Singleton
class ThemeDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val IS_DARK_MODE_KEY = booleanPreferencesKey("is_dark_mode")

    suspend fun saveTheme(isDarkMode: Boolean) {
        context.themeDataStore.edit { preferences ->
            preferences[IS_DARK_MODE_KEY] = isDarkMode
        }
    }

    fun getTheme(): Flow<Boolean> = context.themeDataStore.data.map { preferences ->
        preferences[IS_DARK_MODE_KEY] ?: false
    }
}
