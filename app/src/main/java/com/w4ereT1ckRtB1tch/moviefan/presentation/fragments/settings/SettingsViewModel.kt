package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4ereT1ckRtB1tch.moviefan.data.preferences.PreferenceProvider
import com.w4ereT1ckRtB1tch.moviefan.di.HomeBottomSettings
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    @HomeBottomSettings
    private val homeBottomPanelSettings: PreferenceProvider
) : ViewModel() {

    private val bottomPanel: MutableLiveData<String> = MutableLiveData()
    fun getBottomPanelSettings(): LiveData<String> = bottomPanel

    init {
        initPanelSettings()
    }


    private fun initPanelSettings() {
        bottomPanel.value = homeBottomPanelSettings.restoreCategorySetting()
    }

    fun setBottomPanelCategory(category: String) {
        homeBottomPanelSettings.saveCategorySetting(category)
        initPanelSettings()
    }
}