package uz.gita.mobilebanking_gita.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking_gita.R
import uz.gita.mobilebanking_gita.databinding.ScreenLoginBinding

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {
    private val viewModel: LoginViewModel by viewModels()
    private val viewBinging by viewBinding(ScreenLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loading
            .onEach { }
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .launchIn(lifecycleScope)

        viewModel.message
            .onEach { }
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .launchIn(lifecycleScope)

        viewBinging.btnSigIn.setOnClickListener {
            val phone = viewBinging.phoneNumberEdt.toString()
            val password = viewBinging.passwordEdt.toString()
            viewModel.login(phone, password)
        }
    }
}