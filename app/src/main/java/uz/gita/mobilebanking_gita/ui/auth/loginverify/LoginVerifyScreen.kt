package uz.gita.mobilebanking_gita.ui.auth.loginverify

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking_gita.R
import uz.gita.mobilebanking_gita.databinding.ScreenLoginVerifyBinding

@AndroidEntryPoint
class LoginVerifyScreen : Fragment(R.layout.screen_login_verify) {
    private val navArgs: LoginVerifyScreenArgs by navArgs()
    private val viewBinging by viewBinding(ScreenLoginVerifyBinding::bind)
    private val viewModel: LoginVerifyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinging.btnSend.setOnClickListener {
            //viewModel
            viewBinging.btnSend.setOnClickListener {
                val code = viewBinging.verify.enteredCode //EditText
                val token = navArgs.token
                viewModel.verify(token, 1, code)
            }
        }
    }
}