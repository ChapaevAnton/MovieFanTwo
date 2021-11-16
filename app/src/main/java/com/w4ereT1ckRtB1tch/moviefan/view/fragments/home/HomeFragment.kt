package com.w4ereT1ckRtB1tch.moviefan.view.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentHomeBinding
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import com.w4ereT1ckRtB1tch.moviefan.view.recycler_adapters.HomeCatalogAdapter
import com.w4ereT1ckRtB1tch.moviefan.view.recycler_adapters.ListRecommendAdapter
import com.w4ereT1ckRtB1tch.moviefan.viewmodel.HomeFragmentViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: HomeCatalogAdapter
    private lateinit var recommendAdapter: ListRecommendAdapter
    private lateinit var decorator: SpacingItemDecoration
    private lateinit var decoratorMini: SpacingItemDecoration
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //список рекомендации
        recommendAdapter = ListRecommendAdapter()
        decoratorMini = SpacingItemDecoration(5)
        //каталог фильмов
        adapter =
            HomeCatalogAdapter { film ->
                //слушатель открываем фрагмент и передаем данные
                (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
            }
        decorator = SpacingItemDecoration(10)

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
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 1)

        binding.recommendListFilm.adapter = recommendAdapter
        binding.recommendListFilm.addItemDecoration(decoratorMini)
        viewModel.getRecommendFilms().observe(viewLifecycleOwner) { films ->
            recommendAdapter.items = films
        }

        binding.catalogFilm.adapter = adapter
        binding.catalogFilm.addItemDecoration(decorator)
        viewModel.getFilms().observe(viewLifecycleOwner) { films ->
            adapter.items = films
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}