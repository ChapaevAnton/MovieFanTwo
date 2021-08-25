package com.W4ereT1ckRtB1tch.moviefan.ui.selections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.W4ereT1ckRtB1tch.moviefan.MainActivity
import com.W4ereT1ckRtB1tch.moviefan.data.DataBase
import com.W4ereT1ckRtB1tch.moviefan.ui.utils.AnimationHelper
import com.W4ereT1ckRtB1tch.moviefan.ui.utils.SpacingItemDecoration
import com.w4eret1ckrtb1tch.moviefan.R

class SelectionsFragment : Fragment() {

    private lateinit var selectionCatalogFilmAdapter: SelectionCatalogFilmAdapter
    private lateinit var selectionRecyclerCatalogFilm: RecyclerView
    private lateinit var itemDecorator: SpacingItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectionCatalogFilmAdapter = SelectionCatalogFilmAdapter { film ->
            (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
        }
        selectionCatalogFilmAdapter.addItems(DataBase.filmDataBase)

        itemDecorator = SpacingItemDecoration(10)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 2)

        selectionRecyclerCatalogFilm = view.findViewById(R.id.selections_recycler_catalog_film)
        val selectionsSearch = view.findViewById<SearchView>(R.id.selections_search_top_bar)

        selectionRecyclerCatalogFilm.apply {
            adapter = selectionCatalogFilmAdapter
            addItemDecoration(itemDecorator)
        }

        selectionsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newText?.let {
                    if (it.isEmpty()) {
                        selectionCatalogFilmAdapter.updateItems(DataBase.filmDataBase)
                        return true
                    }
                    selectionCatalogFilmAdapter.updateItems(DataBase.filmDataBase.filter { film ->
                        film.title.lowercase().contains(it.lowercase())
                    })
                }
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        selectionCatalogFilmAdapter.updateItems(DataBase.filmDataBase)
    }

}