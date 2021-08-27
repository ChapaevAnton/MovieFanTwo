package com.w4ereT1ckRtB1tch.moviefan.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.data.DataBase
import com.w4ereT1ckRtB1tch.moviefan.ui.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.ui.utils.SpacingItemDecoration
import com.w4ereT1ckRtB1tch.moviefan.R

class FavoritesFragment : Fragment() {

    private lateinit var favoritesRecyclerCatalogFilm: RecyclerView
    private lateinit var favoritesCatalogFilmAdapter: FavoritesCatalogFilmAdapter
    private lateinit var itemDecorator: SpacingItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesCatalogFilmAdapter = FavoritesCatalogFilmAdapter { film ->
            (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
        }
        //добавление элементов
        favoritesCatalogFilmAdapter.addItems(DataBase.filmDataBase.filter { it.getFavorites() })
        //декоратор
        itemDecorator = SpacingItemDecoration(10)
        Log.d("TAG", "onCreate: FavoritesFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "onCreateView: FavoritesFragment")
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: FavoritesFragment")
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 3)

        favoritesRecyclerCatalogFilm = view.findViewById(R.id.favorites_recycler_catalog_film)

        //иницилизирем список
        favoritesRecyclerCatalogFilm.apply {
            //устанавливаем адаптер
            adapter = favoritesCatalogFilmAdapter
            addItemDecoration(itemDecorator)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: FavoritesFragment")
        favoritesCatalogFilmAdapter.updateDataItems(DataBase.filmDataBase.filter { it.getFavorites() })
    }

}