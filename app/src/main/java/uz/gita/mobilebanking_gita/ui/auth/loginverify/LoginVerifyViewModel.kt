package uz.gita.mobilebanking_gita.ui.auth.loginverify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.mobilebanking_gita.data.remote.request.SignInVerifyRequest
import uz.gita.mobilebanking_gita.data.remote.request.SignUpVerifyRequest
import uz.gita.mobilebanking_gita.repository.AuthRepository
import uz.gita.mobilebanking_gita.ui.NavigationHelper
import uz.gita.mobilebanking_gita.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class LoginVerifyViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navigationHelper: NavigationHelper,
) : ViewModel() {

    fun verify(token: String, where: Int, code: String) {
        if (where == 1) {
            viewModelScope.launch {
                authRepository.signUpVerify(SignUpVerifyRequest(token, code))
                    .collect {
                        when (it) {
                            is ResultData.Success -> {
                                navigationHelper.navigateTo(LoginVerifyScreenDirections.actionLoginVerifyScreenToHomeScreen())
                            }
                            is ResultData.Error -> {
                                //Error
                            }
                            else -> {}
                        }
                    }
            }
        } else {
            viewModelScope.launch {
                authRepository.signInVerify(SignInVerifyRequest(token, code))
                    .collect {
                        when (it) {
                            is ResultData.Success -> {
                                //Next Screen
                            }
                            is ResultData.Error -> {
                                //Error
                            }
                        }
                    }
            }
        }
    }
}