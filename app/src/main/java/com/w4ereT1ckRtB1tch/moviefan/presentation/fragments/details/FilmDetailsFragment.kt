package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentFilmDetailsBinding
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelFactory
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.presentation.MainActivity
import javax.inject.Inject


class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {

    private lateinit var fabRotateClock: Animation
    private lateinit var fabRotateAntiClock: Animation
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<FilmDetailsViewModel>(factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.appComponent.inject(this)
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