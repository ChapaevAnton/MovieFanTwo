package com.w4ereT1ckRtB1tch.moviefan.view.fragments.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.domain.DataBase
import com.w4ereT1ckRtB1tch.moviefan.domain.Film
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentFilmDetailsBinding

class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {

    private var film = ObservableField<Film>()
    private lateinit var fabRotateClock: Animation
    private lateinit var fabRotateAntiClock: Animation
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!
    private val isVisible = ObservableBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        film.set(arguments?.get(MainActivity.ITEM_FILM_DETAILS) as Film)
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.film = film
        binding.onClickedDetails = onClickedDetails
        binding.onClickedFavorites = onClickedFavorites
        binding.onClickedShare = onClickedShare
        binding.isVisible = isVisible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val onClickedDetails = View.OnClickListener {
        if (isVisible.get()) {
            binding.detailsFab.startAnimation(fabRotateClock)
        } else {
            binding.detailsFab.startAnimation(fabRotateAntiClock)
        }
        isVisible.set(!isVisible.get())
    }

    private val onClickedShare = View.OnClickListener {
        val film: Film? = film.get()
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Обязательно посмотри этот фильм:\n" +
                        "Название \"${film?.title}\"\n" +
                        "Описание: ${film?.description}\n" +
                        "Год выпуска: ${film?.year?.year}\n" +
                        "Рейтинг: ${film?.rating}"
            )
            type = "text/plain"
        }
        startActivity(intent)
    }

    private val onClickedFavorites = View.OnClickListener {
        film.get()?.let {
            it.favorites = !it.favorites
        }
        //Log.d("TAG", "DataBase: ${DataBase.filmDataBase}")
    }

}