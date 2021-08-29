package com.w4ereT1ckRtB1tch.moviefan.ui.favorites

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
import com.w4ereT1ckRtB1tch.moviefan.ui.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.ui.utils.SpacingItemDecoration

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private lateinit var filmAdapter: FavoritesCatalogFilmAdapter
    private lateinit var itemDecorator: SpacingItemDecoration
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmAdapter = FavoritesCatalogFilmAdapter { film ->
            (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
        }
        //добавление элементов
        filmAdapter.addItems(DataBase.filmDataBase.filter { it.favorites })
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
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: FavoritesFragment")
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 3)
        //иницилизирем список
        binding.favoritesCatalogFilm.apply {
            //устанавливаем адаптер
            adapter = filmAdapter
            addItemDecoration(itemDecorator)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: FavoritesFragment")
        filmAdapter.updateDataItems(DataBase.filmDataBase.filter { it.favorites })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}