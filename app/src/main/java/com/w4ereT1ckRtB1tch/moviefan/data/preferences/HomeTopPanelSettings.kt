package com.w4ereT1ckRtB1tch.moviefan.data.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProvider
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProviderConfig
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProviderConfig.TopPanel.DEFAULT_CATEGORY_TOP
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProviderConfig.TopPanel.KEY_DEFAULT_CATEGORY_TOP
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProviderConfig.TopPanel.KEY_FIRST_LAUNCH_APP_TOP
import javax.inject.Inject

class HomeTopPanelSettings @Inject constructor(application: Application) :
    PreferenceProvider {

    private val context = application.applicationContext
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            PreferenceProviderConfig.FILE_NAME_SETTINGS,
            Context.MODE_PRIVATE
        )

    init {
        initPreference()
    }

    private fun initPreference() {
        if (sharedPreferences.getBoolean(KEY_FIRST_LAUNCH_APP_TOP, true)) {
            sharedPreferences.edit {
                putString(KEY_DEFAULT_CATEGORY_TOP, DEFAULT_CATEGORY_TOP)
                putBoolean(KEY_FIRST_LAUNCH_APP_TOP, false)
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

}