package uz.gita.mobilebanking_gita.ui.auth.regs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import uz.gita.mobilebanking_gita.repository.AuthRepository
import uz.gita.mobilebanking_gita.ui.NavigationHelper
import uz.gita.mobilebanking_gita.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navigationHelper: NavigationHelper,
) : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    fun regs(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        phoneNumber: String,
        password: String,
        gender: String,
    ) {
        _loading.tryEmit(true)
        authRepository.signup(firstName, lastName, dateOfBirth, phoneNumber, password, gender)
            .onEach {
                _loading.emit(false)
                when (it) {
                    is ResultData.Success -> {
                        navigationHelper.navigateTo(RegistrationScreenDirections.actionRegistrationScreenToLoginVerifyScreen(
                            it.data, 1))
                    }
                    is ResultData.Error -> _message.emit(it.message)
                }
            }
            .launchIn(viewModelScope)
    }
}
