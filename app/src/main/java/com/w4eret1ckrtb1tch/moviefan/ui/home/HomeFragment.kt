package com.W4ereT1ckRtB1tch.moviefan.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.W4ereT1ckRtB1tch.moviefan.MainActivity
import com.W4ereT1ckRtB1tch.moviefan.data.DataBase
import com.W4ereT1ckRtB1tch.moviefan.ui.utils.AnimationHelper
import com.W4ereT1ckRtB1tch.moviefan.ui.utils.SpacingItemDecoration
import com.google.android.material.appbar.MaterialToolbar
import com.w4eret1ckrtb1tch.moviefan.R

class HomeFragment : Fragment() {

    private lateinit var homeCatalogFilmAdapter: HomeCatalogFilmAdapter
    private lateinit var listRecommendAdapter: ListRecommendAdapter
    private lateinit var homeRecyclerListRecommend: RecyclerView
    private lateinit var homeRecyclerCatalogFilm: RecyclerView
    private lateinit var itemDecorator: SpacingItemDecoration
    private lateinit var itemDecoratorMini: SpacingItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //список рекомендации
        listRecommendAdapter = ListRecommendAdapter()
        listRecommendAdapter.addItems(DataBase.filmDataBase.take(6))
        //каталог фильмов
        //создаем адаптер клик на элементе
        homeCatalogFilmAdapter =
            HomeCatalogFilmAdapter { film ->
                //слушатель открываем фрагмент и передаем данные
                (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
            }
        //загружаем БД
        homeCatalogFilmAdapter.addItems(DataBase.filmDataBase)
        //декоратор
        itemDecorator = SpacingItemDecoration(10)
        itemDecoratorMini = SpacingItemDecoration(5)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: HomeFragment")
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 1)
        //верхнее меню
        val mainMenuTopBar = view.findViewById<MaterialToolbar>(R.id.home_menu_top_bar)
        //список рекомендации
        homeRecyclerListRecommend = view.findViewById(R.id.home_recycler_list_recommend)
        homeRecyclerListRecommend.apply {
            adapter = listRecommendAdapter
            addItemDecoration(itemDecoratorMini)
        }
        //список фильмов основной
        homeRecyclerCatalogFilm = view.findViewById(R.id.home_recycler_catalog_film)
        //иницилизирем список
        homeRecyclerCatalogFilm.apply {
            //устанавливаем адаптер
            adapter = homeCatalogFilmAdapter
            addItemDecoration(itemDecorator)
        }
        //обработчик выбора пунктов меню Top Bar
        mainMenuTopBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main_menu_setting -> {
                    (requireActivity() as MainActivity).showSnackBar(R.string.main_menu_settings)
                    true
                }
                else -> false
            }
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: HomeFragment")
        homeCatalogFilmAdapter.updateDataItems(DataBase.filmDataBase)
    }

}