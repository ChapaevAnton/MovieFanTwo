package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentFavoritesBinding
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelFactory
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters.FavoritesAdapter
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class FavoritesFragment : DaggerFragment(R.layout.fragment_favorites) {

    private val adapter by lazy {
        FavoritesAdapter { film -> openFilmDetailsFragment(film) }
    }
    private val decorator by lazy { SpacingItemDecoration(10) }
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<FavoritesViewModel>(factoryProducer = { viewModelFactory })

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
        binding.unbind()
        _binding = null
        super.onDestroyView()
    }

    private fun openFilmDetailsFragment(film: Film?) {
        val action = FavoritesFragmentDirections.actionOpenItemFromFavoritesToDetails(film)
        findNavController().navigate(action)
    }

}