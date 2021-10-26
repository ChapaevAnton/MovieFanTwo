package com.w4ereT1ckRtB1tch.moviefan.domain.preference

interface PreferenceProvider {

    fun saveCategorySetting(category: String)

    fun restoreCategorySetting(): String
}