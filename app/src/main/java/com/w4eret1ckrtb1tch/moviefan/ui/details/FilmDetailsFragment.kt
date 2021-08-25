package com.W4ereT1ckRtB1tch.moviefan.ui.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.W4ereT1ckRtB1tch.moviefan.MainActivity
import com.W4ereT1ckRtB1tch.moviefan.data.DataBase
import com.W4ereT1ckRtB1tch.moviefan.data.Film
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.w4eret1ckrtb1tch.moviefan.R

class FilmDetailsFragment : Fragment() {

    private var film: Film? = null
    private lateinit var fabRotateClock: Animation
    private lateinit var fabRotateAntiClock: Animation
    private lateinit var detailsFavoriteFab: FloatingActionButton
    private lateinit var detailsShareFab: FloatingActionButton
    private lateinit var detailsFab: FloatingActionButton

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
        return inflater.inflate(R.layout.fragment_item_film_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsTitle: CollapsingToolbarLayout = view.findViewById(R.id.details_title_film)
        val detailsPoster: AppCompatImageView = view.findViewById(R.id.details_poster_film)
        val detailsDescription: TextView = view.findViewById(R.id.details_description_film)
        //fab
        detailsFavoriteFab = view.findViewById(R.id.details_favorites_film_fab)
        detailsShareFab = view.findViewById(R.id.details_share_film_fab)
        detailsFab = view.findViewById(R.id.details_film_fab)

        film?.let {
            detailsTitle.title = it.title
            Glide.with(view).load(it.poster).centerCrop().into(detailsPoster)
            detailsDescription.text = it.description
            detailsFavoriteFab.setImageResource(if (it.isFavorites) R.drawable.ic_round_favorite_24 else R.drawable.ic_round_favorite_border_24)
        }

        //кнопка действия fab
        detailsFab.setOnClickListener {
            onClickDetailsFub()
        }

        //добавить в избранное
        detailsFavoriteFab.setOnClickListener {
            film?.let {
                if (it.isFavorites) {
                    it.isFavorites = false
                    detailsFavoriteFab.setImageResource(R.drawable.ic_round_favorite_border_24)
                } else {
                    it.isFavorites = true
                    detailsFavoriteFab.setImageResource(R.drawable.ic_round_favorite_24)
                }
            }
            Log.d("TAG", "DataBase: ${DataBase.filmDataBase}")
        }

        //поделиться информацией о фильме
        detailsShareFab.setOnClickListener {
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

    private fun onClickDetailsFub() {
        if (detailsFavoriteFab.isVisible && detailsShareFab.isVisible) {
            detailsFavoriteFab.hide()
            detailsShareFab.hide()
            detailsFab.startAnimation(fabRotateClock)
        } else {
            detailsFavoriteFab.show()
            detailsShareFab.show()
            detailsFab.startAnimation(fabRotateAntiClock)
        }
    }

}