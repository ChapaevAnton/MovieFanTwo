package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.selections

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentSelectionsBinding
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelFactory
import com.w4ereT1ckRtB1tch.moviefan.presentation.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters.SelectionAdapter
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import javax.inject.Inject


class SelectionsFragment : Fragment(R.layout.fragment_selections) {

    private lateinit var adapter: SelectionAdapter
    private lateinit var decorator: SpacingItemDecoration
    private var _binding: FragmentSelectionsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<SelectionViewModel>(factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SelectionAdapter { film ->
            (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
        }
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
        viewModel.getFilms().observe(viewLifecycleOwner) { films -> adapter.items = films }
        binding.searchTopBar.setOnQueryTextListener(viewModel.onQueryTextListener())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}