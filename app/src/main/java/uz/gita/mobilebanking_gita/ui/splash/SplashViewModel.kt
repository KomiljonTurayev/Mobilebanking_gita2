package uz.gita.mobilebanking_gita.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.mobilebanking_gita.repository.AuthRepository
import uz.gita.mobilebanking_gita.ui.NavigationHelper
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navigationHelper: NavigationHelper,
) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(1000)
            when {
                authRepository.isFirstLaunch() -> {
                    navigationHelper.navigateTo(SplashScreenDirections.actionSplashScreenToTermsScreen())
                }
                authRepository.isSinged() -> {
                    navigationHelper.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
                }
                else -> {
                    navigationHelper.navigateTo(SplashScreenDirections.actionSplashScreenToRegistrationScreen())
                }
            }
        }
    }

    fun demo() {

    }
}