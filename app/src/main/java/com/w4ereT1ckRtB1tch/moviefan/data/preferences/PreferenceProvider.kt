package com.w4ereT1ckRtB1tch.moviefan.data.preferences

interface PreferenceProvider {

    fun saveCategorySetting(category: String)
    fun restoreCategorySetting(): String

    companion object {
        const val FILE_NAME_SETTINGS = "settings"
    }
}