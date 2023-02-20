package plumber.online.test.ru.presentation.screens.main

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import plumber.online.test.ru.R
import plumber.online.test.ru.databinding.MainFragmentBinding
import plumber.online.test.ru.presentation.base.BaseFragment
import plumber.online.test.ru.presentation.utils.Utils
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

    override fun onStart() {
        super.onStart()
        viewModel.load()
    }

    override fun initialize() {
        super.initialize()
        viewModel.load()
    }

    private fun setupStateFlow() {
        viewModel.stateFlow.onEach { state ->
            with(binding){
                infoLocationTxt.text = when(state) {
                    is MainState.Loaded -> state.data
                    is MainState.Nothing -> state.data
                    else -> ""
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    private fun setupActionFlow() {
        viewModel.actionFlow.onEach { action ->
            with(binding){
                if(action is MainAction.RouteToMap) {
                    findNavController().navigate(R.id.action_mainFragment_to_mapFragment)
                }
                if(action is MainAction.ShowError) {
                    context?.let {
                        AlertDialog
                            .Builder(it)
                            .setTitle("Error")
                            .setMessage(action.message)
                            .setNegativeButton("Ok") { _, _ -> }
                            .show()
                    }
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setupListeners() {
        binding.showOnMapBtn.setOnClickListener {
            if (Utils.isNetworkAvailable(this@MainFragment.requireContext())) {
                viewModel.routeToMapScreen()
            } else {
                viewModel.sendAction(MainAction.ShowError("Отсутствует интернет"))
            }
        }
    }
}