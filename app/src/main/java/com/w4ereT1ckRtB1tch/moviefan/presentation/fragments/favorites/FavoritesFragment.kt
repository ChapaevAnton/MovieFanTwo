package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentFavoritesBinding
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelFactory
import com.w4ereT1ckRtB1tch.moviefan.presentation.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters.FavoritesAdapter
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class FavoritesFragment : DaggerFragment(R.layout.fragment_favorites) {

    private lateinit var adapter: FavoritesAdapter
    private lateinit var decorator: SpacingItemDecoration
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<FavoritesViewModel>(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavoritesAdapter { film ->
            (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
        }
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

    override fun onResume() {
        super.onResume()
        viewModel.setFavoritesFilms()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: FavoritesFragment")
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 3)
        binding.favoritesCatalogFilm.adapter = adapter
        binding.favoritesCatalogFilm.addItemDecoration(decorator)
        viewModel.getFavoritesFilms().observe(viewLifecycleOwner) { films ->
            binding.isEmptyList = films.isEmpty()
            adapter.items = films
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}