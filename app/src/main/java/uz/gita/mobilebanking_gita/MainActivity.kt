package uz.gita.mobilebanking_gita

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking_gita.databinding.ActivityMainBinding
import uz.gita.mobilebanking_gita.ui.NavigationHelper
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationHelper: NavigationHelper
    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) { ActivityMainBinding.inflate(layoutInflater) }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { viewBinding.navHostFragment.findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(viewBinding.root)

        navigationHelper.navigationBuffer
            .onEach { it(navController) }
            .launchIn(lifecycleScope)
    }
}