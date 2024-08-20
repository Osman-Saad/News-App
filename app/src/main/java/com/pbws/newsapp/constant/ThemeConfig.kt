package com.pbws.newsapp.constant

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "themeConfig")

@Singleton
class ThemeConfig @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val THEME_CONFIG = booleanPreferencesKey("reverse_system_theme")

    val preferencesFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_CONFIG] ?: false
        }

    suspend fun saveThemeConfig(value: Boolean) {
        context.dataStore.edit { themeConfig ->
            themeConfig[THEME_CONFIG] = value
        }
    }
}