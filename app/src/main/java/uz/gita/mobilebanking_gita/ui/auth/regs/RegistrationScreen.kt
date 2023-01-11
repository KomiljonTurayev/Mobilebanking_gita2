package uz.gita.mobilebanking_gita.ui.auth.regs

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
import uz.gita.mobilebanking_gita.databinding.ScreenRegsBinding

@AndroidEntryPoint
class RegistrationScreen : Fragment(R.layout.screen_regs) {
    private val viewBinding by viewBinding(ScreenRegsBinding::bind)
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.loading
            .onEach { }
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .launchIn(lifecycleScope)

        viewModel.message
            .onEach { }
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .launchIn(lifecycleScope)

        viewBinding.btnRegs.setOnClickListener {
            val firstName = viewBinding.firstNameEdt.text.toString()
            val lastName = viewBinding.lastNameEdt.text.toString()
            val dateOfBirth = viewBinding.dateBirthEdt.text.toString()
            val phoneNumber = viewBinding.phoneNumberEdt.text.toString()
            val password = viewBinding.passwordEdt.text.toString()
            viewModel.regs(firstName, lastName, dateOfBirth, phoneNumber, password,"0")
        }
    }
}