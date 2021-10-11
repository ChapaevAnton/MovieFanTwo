package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentSelectionsBinding
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelFactory
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters.SelectionAdapter
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class SelectionsFragment : DaggerFragment(R.layout.fragment_selections) {

    private lateinit var adapter: SelectionAdapter
    private lateinit var decorator: SpacingItemDecoration
    private var _binding: FragmentSelectionsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<SelectionViewModel>(factoryProducer = { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SelectionAdapter { film -> openFilmDetailsFragment(film) }
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
        //Поддержка вариантов панели приложений
        val navController = findNavController()
        val appBarConfig = AppBarConfiguration(navController.graph)
        binding.menuTopBarSelections.setupWithNavController(navController, appBarConfig)
        //анимация открытия фрагмента
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 2)
        binding.selectionsCatalogFilm.adapter = adapter
        binding.selectionsCatalogFilm.addItemDecoration(decorator)
        viewModel.getFilms().observe(viewLifecycleOwner) { films -> adapter.items = films }
        binding.searchTopBar.setOnQueryTextListener(viewModel.onQueryTextListener())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openFilmDetailsFragment(film: Film) {
        val action = SelectionsFragmentDirections.actionOpenItemFromSelectionsToDetails(film)
        findNavController().navigate(action)
    }

}