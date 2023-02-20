package plumber.online.test.ru.presentation.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import plumber.online.test.ru.R
import plumber.online.test.ru.databinding.MainFragmentBinding
import plumber.online.test.ru.presentation.base.BaseFragment
import plumber.online.test.ru.presentation.utils.observeInLifecycle

@AndroidEntryPoint
class MainFragment: BaseFragment<MainViewModel, MainFragmentBinding>(R.layout.main_fragment) {

    override val viewModel: MainViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MainFragmentBinding = MainFragmentBinding.inflate(inflater)

    override fun setupSubscribers() {
        setupStateFlow()
        setupActionFlow()
    }

    private fun setupStateFlow() {
        viewModel.stateFlow.onEach {
            with(binding){

            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    private fun setupActionFlow() {
        viewModel.stateFlow.onEach { state ->
            with(binding){

            }
        }.observeInLifecycle(viewLifecycleOwner)
    }



}