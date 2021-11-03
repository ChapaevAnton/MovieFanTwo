package com.w4ereT1ckRtB1tch.moviefan.data.preferences

import com.w4ereT1ckRtB1tch.moviefan.data.source.MoviesConfig

object PreferenceProviderConfig {

    const val FILE_NAME_SETTINGS = "settings"

    object TopPanel {
        const val KEY_FIRST_LAUNCH_APP_TOP = "first_launch_app_top"
        const val KEY_DEFAULT_CATEGORY_TOP = "home_default_category_top"
        const val DEFAULT_CATEGORY_TOP = MoviesConfig.Path.UPCOMING_CATEGORY
    }

    object BottomPanel {
        const val KEY_FIRST_LAUNCH_APP_BOTTOM = "first_launch_app_bottom"
        const val KEY_DEFAULT_CATEGORY_BOTTOM = "home_default_category_bottom"
        const val DEFAULT_CATEGORY_BOTTOM = MoviesConfig.Path.POPULAR_CATEGORY
    }
}