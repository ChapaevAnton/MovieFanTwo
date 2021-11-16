package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentSelectionsBinding
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelFactory
import com.w4ereT1ckRtB1tch.moviefan.domain.model.Film
import com.w4ereT1ckRtB1tch.moviefan.presentation.adapters.SelectionAdapter
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class SelectionsFragment : DaggerFragment(R.layout.fragment_selections) {

    private val adapter by lazy {
        SelectionAdapter { film ->
            openFilmDetailsFragment(film)
        }
    }
    private val decorator by lazy { SpacingItemDecoration(10) }
    private var _binding: FragmentSelectionsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<SelectionViewModel>(factoryProducer = { viewModelFactory })

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
        viewModel.getFilms().observe(viewLifecycleOwner) { films -> adapter.items = films }
        binding.searchTopBar.setOnQueryTextListener(viewModel.onQueryTextListener())
    }

    override fun onDestroyView() {
        binding.unbind()
        _binding = null
        super.onDestroyView()
    }

    private fun openFilmDetailsFragment(film: Film?) {
        val action = SelectionsFragmentDirections.actionOpenItemFromSelectionsToDetails(film)
        findNavController().navigate(action)
    }

}