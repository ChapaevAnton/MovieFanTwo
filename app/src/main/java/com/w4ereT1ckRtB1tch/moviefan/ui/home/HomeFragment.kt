package com.w4ereT1ckRtB1tch.moviefan.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.data.DataBase
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentHomeBinding
import com.w4ereT1ckRtB1tch.moviefan.ui.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.ui.utils.SpacingItemDecoration

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var filmAdapter: HomeCatalogFilmAdapter
    private lateinit var listdapter: ListRecommendAdapter
    private lateinit var itemDecorator: SpacingItemDecoration
    private lateinit var itemDecoratorMini: SpacingItemDecoration
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //список рекомендации
        listdapter = ListRecommendAdapter()
        listdapter.addItems(DataBase.filmDataBase.take(6))
        //каталог фильмов
        //создаем адаптер клик на элементе
        filmAdapter =
            HomeCatalogFilmAdapter { film ->
                //слушатель открываем фрагмент и передаем данные
                (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
            }
        //загружаем БД
        filmAdapter.addItems(DataBase.filmDataBase)
        //декоратор
        itemDecorator = SpacingItemDecoration(10)
        itemDecoratorMini = SpacingItemDecoration(5)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: HomeFragment")
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 1)
        //верхнее меню
        //список рекомендации
        binding.recommendListFilm.apply {
            adapter = listdapter
            addItemDecoration(itemDecoratorMini)
        }
        //список фильмов основной
        //иницилизирем список
        binding.catalogFilm.apply {
            //устанавливаем адаптер
            adapter = filmAdapter
            addItemDecoration(itemDecorator)
        }
        //обработчик выбора пунктов меню Top Bar
        binding.menuTopBar.setOnMenuItemClickListener { menuItem ->
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
        filmAdapter.updateDataItems(DataBase.filmDataBase)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}