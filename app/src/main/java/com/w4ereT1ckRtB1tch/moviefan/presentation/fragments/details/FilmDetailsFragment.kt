package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.w4ereT1ckRtB1tch.moviefan.presentation.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentFilmDetailsBinding
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film

class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {

    private lateinit var fabRotateClock: Animation
    private lateinit var fabRotateAntiClock: Animation
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(FilmDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setFilm(arguments?.get(MainActivity.ITEM_FILM_DETAILS) as Film)
        fabRotateClock =
            AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_clock_animation)
        fabRotateAntiClock =
            AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_anti_clock_animation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilm().observe(viewLifecycleOwner) { film ->
            Log.d("TAG", "getFilm(): $film")
            binding.film = film
        }

        viewModel.isVisible().observe(viewLifecycleOwner) { visible ->
            binding.apply {
                if (visible)
                    detailsFab.startAnimation(fabRotateAntiClock)
                else
                    detailsFab.startAnimation(fabRotateClock)
                isVisible = visible
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}