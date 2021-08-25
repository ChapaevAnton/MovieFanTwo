package com.w4ereT1ckRtB1tch.moviefan.ui.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.data.DataBase
import com.w4ereT1ckRtB1tch.moviefan.data.Film
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentFilmDetailsBinding

class FilmDetailsFragment : Fragment() {

    private var film: Film? = null
    private lateinit var fabRotateClock: Animation
    private lateinit var fabRotateAntiClock: Animation
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        film = arguments?.get(MainActivity.ITEM_FILM_DETAILS) as Film
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
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_film_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        film?.let {
            binding.film = it
        }
        //кнопка действия fab
        binding.detailsFab.setOnClickListener {
            onClickDetailsFub()
        }

        //добавить в избранное
        binding.favoritesFab.setOnClickListener {
            film?.let {
                if (it.isFavorites) {
                    it.isFavorites = false
                    binding.favoritesFab.setImageResource(R.drawable.ic_round_favorite_border_24)
                } else {
                    it.isFavorites = true
                    binding.favoritesFab.setImageResource(R.drawable.ic_round_favorite_24)
                }
            }
            Log.d("TAG", "DataBase: ${DataBase.filmDataBase}")
        }

        //поделиться информацией о фильме
        binding.shareFab.setOnClickListener {
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onClickDetailsFub() {
        if (binding.favoritesFab.isVisible && binding.shareFab.isVisible) {
            binding.favoritesFab.hide()
            binding.shareFab.hide()
            binding.detailsFab.startAnimation(fabRotateClock)
        } else {
            binding.favoritesFab.show()
            binding.shareFab.show()
            binding.detailsFab.startAnimation(fabRotateAntiClock)
        }
    }

}