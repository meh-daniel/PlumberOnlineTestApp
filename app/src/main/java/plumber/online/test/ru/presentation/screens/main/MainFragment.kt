package plumber.online.test.ru.presentation.screens.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.LocationServices
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

    private fun setupStateFlow() {
        viewModel.stateFlow.onEach { state ->
            with(binding){
                infoLocationTxt.text = if(state is MainState.Loaded) state.data else ""
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    private fun setupActionFlow() {
        viewModel.actionFlow.onEach { action ->
            with(binding){
                if(action is MainAction.RouteToMap) {
                    findNavController().navigate(R.id.action_mainFragment_to_mapFragment)
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setupListeners() {
        binding.showOnMapBtn.setOnClickListener {
            if (Utils.isNetworkAvailable(this@MainFragment.requireContext())) {
                viewModel.sendAction(MainAction.RouteToMap)
            } else {
                viewModel.sendAction(MainAction.ShowError("Отсутствие интернета"))
            }
        }
    }
}