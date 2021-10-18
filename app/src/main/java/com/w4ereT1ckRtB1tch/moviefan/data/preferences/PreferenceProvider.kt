package com.w4ereT1ckRtB1tch.moviefan.data.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.w4ereT1ckRtB1tch.moviefan.data.source.MoviesConfig
import javax.inject.Inject

class PreferenceProvider @Inject constructor(application: Application) {

    private val context = application.applicationContext
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(FILE_NAME_SETTINGS, Context.MODE_PRIVATE)

    init {
        initPreference()
    }

    private fun initPreference() {
        if (sharedPreferences.getBoolean(KEY_FIRST_LAUNCH_APP, true)) {
            sharedPreferences.edit {
                putString(KEY_DEFAULT_CATEGORY_TOP, DEFAULT_CATEGORY_TOP)
                putString(KEY_DEFAULT_CATEGORY_BOTTOM, DEFAULT_CATEGORY_BOTTOM)
                putBoolean(KEY_FIRST_LAUNCH_APP, false)
            }
        }
    }

    fun restoreDefaultPanelTop(): String = sharedPreferences.getString(
        KEY_DEFAULT_CATEGORY_TOP,
        DEFAULT_CATEGORY_TOP
    ) ?: DEFAULT_CATEGORY_TOP

    fun saveDefaultPanelBottom(category: String) {
        sharedPreferences.edit { putString(KEY_DEFAULT_CATEGORY_BOTTOM, category) }
    }

    fun restoreDefaultPanelBottom(): String = sharedPreferences.getString(
        KEY_DEFAULT_CATEGORY_BOTTOM,
        DEFAULT_CATEGORY_BOTTOM
    ) ?: DEFAULT_CATEGORY_BOTTOM

    companion object {
        private const val FILE_NAME_SETTINGS = "settings"
        private const val KEY_FIRST_LAUNCH_APP = "first_launch_app"
        private const val KEY_DEFAULT_CATEGORY_BOTTOM = "home_default_category_bottom"
        private const val KEY_DEFAULT_CATEGORY_TOP = "home_default_category_top"
        private const val DEFAULT_CATEGORY_TOP = MoviesConfig.Path.UPCOMING_CATEGORY
        private const val DEFAULT_CATEGORY_BOTTOM = MoviesConfig.Path.POPULAR_CATEGORY
    }
}