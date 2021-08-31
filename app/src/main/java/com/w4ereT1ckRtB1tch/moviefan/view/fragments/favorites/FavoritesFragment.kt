package com.w4ereT1ckRtB1tch.moviefan.view.fragments.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.data.DataBase
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentFavoritesBinding
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import com.w4ereT1ckRtB1tch.moviefan.view.recycler_adapters.FavoritesCatalogAdapter

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private lateinit var adapter: FavoritesCatalogAdapter
    private lateinit var decorator: SpacingItemDecoration
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavoritesCatalogAdapter { film ->
            (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
        }
        //добавление элементов
        adapter.items = DataBase.filmDataBase.filter { it.favorites }
        //декоратор
        decorator = SpacingItemDecoration(10)
        Log.d("TAG", "onCreate: FavoritesFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "onCreateView: FavoritesFragment")
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: FavoritesFragment")
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 3)
        //иницилизирем список
        //устанавливаем адаптер
        binding.favoritesCatalogFilm.adapter = adapter
        binding.favoritesCatalogFilm.addItemDecoration(decorator)
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: FavoritesFragment")
        adapter.items = DataBase.filmDataBase.filter { it.favorites }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}