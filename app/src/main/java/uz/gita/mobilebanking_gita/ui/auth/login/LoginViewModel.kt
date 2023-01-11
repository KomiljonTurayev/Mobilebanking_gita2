package uz.gita.mobilebanking_gita.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import uz.gita.mobilebanking_gita.repository.AuthRepository
import uz.gita.mobilebanking_gita.ui.NavigationHelper
import uz.gita.mobilebanking_gita.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navigationHelper: NavigationHelper,
) : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun login(phone: String, password: String) {
        if (phone.isEmpty()) {
            _message.tryEmit("Phone kirtilmagan")
            return
        }
        if (password.length !in 5..20) {
            _message.tryEmit("Parol uzunligi shartlarga mos emas")
            return
        }
        _loading.tryEmit(true)
        authRepository.login(phone, password)
            .onEach {
                _loading.emit(false)
                when (it) {
                    is ResultData.Success -> {
                        navigationHelper.navigateTo(LoginScreenDirections.actionLoginScreenToLoginVerifyScreen(
                            it.data,
                            2))
                    }
                    is ResultData.Error -> _message.emit(it.message)
                }
            }
            .launchIn(viewModelScope)
    }
}