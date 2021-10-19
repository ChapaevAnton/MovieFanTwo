package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.data.source.MoviesConfig
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentSettingsBinding
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingsFragment : DaggerFragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<SettingsViewModel>(factoryProducer = { viewModelFactory })

    override

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBottomPanelCategory().observe(viewLifecycleOwner) { category ->
            when (category) {
                MoviesConfig.Path.POPULAR_CATEGORY -> binding.homeSettings.check(R.id.popular)
                MoviesConfig.Path.TOP_RATED_CATEGORY -> binding.homeSettings.check(R.id.top_rated)
            }
        }
        binding.homeSettings.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.popular -> viewModel.setBottomPanelCategory(MoviesConfig.Path.POPULAR_CATEGORY)
                R.id.top_rated -> viewModel.setBottomPanelCategory(MoviesConfig.Path.TOP_RATED_CATEGORY)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}