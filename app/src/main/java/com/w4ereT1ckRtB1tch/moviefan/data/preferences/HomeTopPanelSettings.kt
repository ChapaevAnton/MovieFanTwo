package com.w4ereT1ckRtB1tch.moviefan.data.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.w4ereT1ckRtB1tch.moviefan.data.source.MoviesConfig
import javax.inject.Inject

class HomeTopPanelSettings @Inject constructor(application: Application) :
    PreferenceProvider {

    private val context = application.applicationContext
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            PreferenceProvider.FILE_NAME_SETTINGS,
            Context.MODE_PRIVATE
        )

    init {
        initPreference()
    }

    private fun initPreference() {
        if (sharedPreferences.getBoolean(KEY_FIRST_LAUNCH_APP, true)) {
            sharedPreferences.edit {
                putString(KEY_DEFAULT_CATEGORY_TOP, DEFAULT_CATEGORY_TOP)
                putBoolean(KEY_FIRST_LAUNCH_APP, false)
            }
        }
    }

    override fun saveCategorySetting(category: String) {
        sharedPreferences.edit { putString(KEY_DEFAULT_CATEGORY_TOP, category) }
    }

    override fun restoreCategorySetting(): String = sharedPreferences.getString(
        KEY_DEFAULT_CATEGORY_TOP,
        DEFAULT_CATEGORY_TOP
    ) ?: DEFAULT_CATEGORY_TOP

    companion object {
        private const val KEY_FIRST_LAUNCH_APP = "first_launch_app_top"
        private const val KEY_DEFAULT_CATEGORY_TOP = "home_default_category_top"
        private const val DEFAULT_CATEGORY_TOP = MoviesConfig.Path.UPCOMING_CATEGORY
    }
}