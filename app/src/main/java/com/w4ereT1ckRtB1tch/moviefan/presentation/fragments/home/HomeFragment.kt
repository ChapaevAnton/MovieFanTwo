package com.w4ereT1ckRtB1tch.moviefan.presentation.fragments.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.w4ereT1ckRtB1tch.moviefan.App
import com.w4ereT1ckRtB1tch.moviefan.R
import com.w4ereT1ckRtB1tch.moviefan.databinding.FragmentHomeBinding
import com.w4ereT1ckRtB1tch.moviefan.di.viewmodel.ViewModelFactory
import com.w4ereT1ckRtB1tch.moviefan.presentation.MainActivity
import com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters.FooterStateAdapter
import com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters.HomeAdapter
import com.w4ereT1ckRtB1tch.moviefan.presentation.recycler_adapters.UpcomingAdapter
import com.w4ereT1ckRtB1tch.moviefan.utils.AnimationHelper
import com.w4ereT1ckRtB1tch.moviefan.utils.SpacingItemDecoration
import javax.inject.Inject


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: HomeAdapter
    private lateinit var upcomingAdapter: UpcomingAdapter
    private lateinit var decorator: SpacingItemDecoration
    private lateinit var decoratorMini: SpacingItemDecoration
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<HomeViewModel>(factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.instance.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //список рекомендации
        upcomingAdapter = UpcomingAdapter()
        decoratorMini = SpacingItemDecoration(5)
        //каталог фильмов
        adapter =
            HomeAdapter { film ->
                //слушатель открываем фрагмент и передаем данные
                (requireActivity() as MainActivity).launchFilmDetailsFragment(film)
            }
        decorator = SpacingItemDecoration(10)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onViewCreated: HomeFragment")
        AnimationHelper.performFragmentCircularRevealAnimation(view, requireActivity(), 1)
        //upcoming film
        binding.upcomingFilm.adapter = upcomingAdapter
        binding.upcomingFilm.addItemDecoration(decoratorMini)
        viewModel.getUpcomingFilms()
            .observe(viewLifecycleOwner) { films ->
                upcomingAdapter.submitData(lifecycle, films)
            }
        //popular film
        binding.catalogFilm.adapter =
            adapter.withLoadStateFooter(FooterStateAdapter { adapter.retry() })
        adapter.addLoadStateListener { loadState ->
            Log.d("TAG", "onLoadingPopularData: ok")
            with(binding) {
                swipeRefresh.isRefreshing = loadState.refresh is LoadState.Loading
                errorIsVisible = loadState.refresh is LoadState.Error
                if (loadState.refresh is LoadState.Error) {
                    include.errorMessage.text =
                        (loadState.source.refresh as LoadState.Error).error.localizedMessage
                    include.retryLoad.setOnClickListener { adapter.retry() }
                }
            }
        }
        binding.swipeRefresh.setOnRefreshListener { viewModel.onRefreshPopularData() }
        binding.catalogFilm.addItemDecoration(decorator)
        viewModel.getPopularFilms().observe(viewLifecycleOwner) { films ->
            adapter.submitData(lifecycle, films)
        }

        //обработчик выбора пунктов меню Top Bar
        binding.menuTopBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main_menu_setting -> {
                    (requireActivity() as MainActivity).showSnackBar(R.string.main_menu_settings)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}