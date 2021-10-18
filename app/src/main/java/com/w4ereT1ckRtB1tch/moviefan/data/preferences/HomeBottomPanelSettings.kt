package com.w4ereT1ckRtB1tch.moviefan.data.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.w4ereT1ckRtB1tch.moviefan.data.source.MoviesConfig
import javax.inject.Inject

class HomeBottomPanelSettings @Inject constructor(application: Application) :
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
                putString(KEY_DEFAULT_CATEGORY_BOTTOM, DEFAULT_CATEGORY_BOTTOM)
                putBoolean(KEY_FIRST_LAUNCH_APP, false)
            }
        }
    }

    override fun saveCategorySetting(category: String) {
        sharedPreferences.edit { putString(KEY_DEFAULT_CATEGORY_BOTTOM, category) }
    }

    override fun restoreCategorySetting(): String = sharedPreferences.getString(
        KEY_DEFAULT_CATEGORY_BOTTOM,
        DEFAULT_CATEGORY_BOTTOM
    ) ?: DEFAULT_CATEGORY_BOTTOM

    companion object {
        private const val KEY_FIRST_LAUNCH_APP = "first_launch_app_bottom"
        private const val KEY_DEFAULT_CATEGORY_BOTTOM = "home_default_category_bottom"
        private const val DEFAULT_CATEGORY_BOTTOM = MoviesConfig.Path.POPULAR_CATEGORY
    }
}