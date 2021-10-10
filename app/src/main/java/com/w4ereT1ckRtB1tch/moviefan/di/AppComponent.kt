package com.w4ereT1ckRtB1tch.moviefan.di

import android.app.Application
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelModule
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details.FilmDetailsFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites.FavoritesFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home.HomeFragment
import com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections.SelectionsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataBaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(selectionsFragment: SelectionsFragment)
    fun inject(filmDetailsFragment: FilmDetailsFragment)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}