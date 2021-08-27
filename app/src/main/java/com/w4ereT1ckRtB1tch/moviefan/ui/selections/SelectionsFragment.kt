package com.w4ereT1ckRtB1tch.moviefan.ui.selections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.data.DataBase
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentSelectionsBinding
import com.w4ereT1ckRtB1tch.moviefan.ui.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.ui.utils.SpacingItemDecoration

class SelectionsFragment : Fragment() {

    private lateinit var filmAdapter: SelectionCatalogFilmAdapter
    private lateinit var itemDecorator: SpacingItemDecoration
    private lateinit var binding: FragmentSelectionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmAdapter = SelectionCatalogFilmAdapter { film ->
            (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
        }
        filmAdapter.addItems(DataBase.filmDataBase)
        itemDecorator = SpacingItemDecoration(10)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selections, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 2)
        binding.selectionsCatalogFilm.apply {
            adapter = filmAdapter
            addItemDecoration(itemDecorator)
        }
        binding.searchTopBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isEmpty()) {
                        filmAdapter.updateItems(DataBase.filmDataBase)
                        return true
                    }
                    filmAdapter.updateItems(DataBase.filmDataBase.filter { film ->
                        film.title.lowercase().contains(it.lowercase())
                    })
                }
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        filmAdapter.updateItems(DataBase.filmDataBase)
    }

}