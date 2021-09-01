package com.w4ereT1ckRtB1tch.moviefan.view.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentFilmDetailsBinding
import com.w4ereT1ckRtB1tch.moviefan.domain.Film
import com.w4ereT1ckRtB1tch.moviefan.viewmodel.FilmDetailsFragmentViewModel

class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {

    private lateinit var fabRotateClock: Animation
    private lateinit var fabRotateAntiClock: Animation
    private var isVisibleButton = ObservableBoolean(false)
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(FilmDetailsFragmentViewModel::class.java)
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
        binding.isVisibleButton = isVisibleButton
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilm().observe(viewLifecycleOwner) { film ->
            Log.d("TAG", "getFilm(): $film")
            binding.film = film
        }

        viewModel.getDetails().observe(viewLifecycleOwner) { event ->
            if (event.isHandled) {
                if (isVisibleButton.get()) {
                    binding.detailsFab.startAnimation(fabRotateClock)
                } else {
                    binding.detailsFab.startAnimation(fabRotateAntiClock)
                }
                isVisibleButton.set(!isVisibleButton.get())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


// TODO: 01.09.2021 Перенести во ViewModel
//    private val onClickedShare = View.OnClickListener {
//        val film: Film? = film.get()
//        val intent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(
//                Intent.EXTRA_TEXT,
//                "Обязательно посмотри этот фильм:\n" +
//                        "Название \"${film?.title}\"\n" +
//                        "Описание: ${film?.description}\n" +
//                        "Год выпуска: ${film?.year?.year}\n" +
//                        "Рейтинг: ${film?.rating}"
//            )
//            type = "text/plain"
//        }
//        startActivity(intent)
//    }
//

}