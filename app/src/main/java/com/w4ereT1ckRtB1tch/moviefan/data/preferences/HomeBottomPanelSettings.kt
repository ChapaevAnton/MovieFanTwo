package com.w4ereT1ckRtB1tch.moviefan.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.w4ereT1ckRtB1tch.moviefan.domain.preference.PreferenceProvider
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.PreferenceProviderConfig.BottomPanel.DEFAULT_CATEGORY_BOTTOM
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.PreferenceProviderConfig.BottomPanel.KEY_DEFAULT_CATEGORY_BOTTOM
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.PreferenceProviderConfig.BottomPanel.KEY_FIRST_LAUNCH_APP_BOTTOM
import javax.inject.Inject

class HomeBottomPanelSettings @Inject constructor(context: Context) :
    PreferenceProvider {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            PreferenceProviderConfig.FILE_NAME_SETTINGS,
            Context.MODE_PRIVATE
        )

    init {
        initPreference()
    }

    private fun initPreference() {
        if (sharedPreferences.getBoolean(KEY_FIRST_LAUNCH_APP_BOTTOM, true)) {
            sharedPreferences.edit {
                putString(KEY_DEFAULT_CATEGORY_BOTTOM, DEFAULT_CATEGORY_BOTTOM)
                putBoolean(KEY_FIRST_LAUNCH_APP_BOTTOM, false)
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

}