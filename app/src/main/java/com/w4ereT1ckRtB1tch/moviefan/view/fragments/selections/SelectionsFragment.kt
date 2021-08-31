package com.w4ereT1ckRtB1tch.moviefan.view.fragments.selections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.w4ereT1ckRtB1tch.moviefan.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.domain.DataBase
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentSelectionsBinding
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import com.w4ereT1ckRtB1tch.moviefan.view.recycler_adapters.SelectionCatalogAdapter

class SelectionsFragment : Fragment(R.layout.fragment_selections) {

    private lateinit var adapter: SelectionCatalogAdapter
    private lateinit var decorator: SpacingItemDecoration
    private var _binding: FragmentSelectionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SelectionCatalogAdapter { film ->
            (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
        }
        //adapter.items = DataBase.filmDataBase
        decorator = SpacingItemDecoration(10)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 2)
        binding.selectionsCatalogFilm.adapter = adapter
        binding.selectionsCatalogFilm.addItemDecoration(decorator)
        binding.searchTopBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isEmpty()) {
                        //adapter.items = DataBase.filmDataBase
                        return true
                    }
                    // adapter.items = DataBase.filmDataBase.filter { film ->
                    //     film.title.lowercase().contains(it.lowercase())
                    //  }
                }
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //adapter.items = DataBase.filmDataBase
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}