package uz.gita.mobilebanking_gita.ui.terms

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.mobilebanking_gita.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class TermsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    fun accept() {
        authRepository.disableFirstLaunch()
    }
}