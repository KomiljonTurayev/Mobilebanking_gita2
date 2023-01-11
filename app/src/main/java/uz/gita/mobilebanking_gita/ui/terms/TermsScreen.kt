package uz.gita.mobilebanking_gita.ui.terms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking_gita.R
import uz.gita.mobilebanking_gita.databinding.ScreenTermsBinding

@AndroidEntryPoint
class TermsScreen : Fragment(R.layout.screen_terms) {
    private val viewBinding by viewBinding(ScreenTermsBinding::bind)
    private val viewModel: TermsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnAccept.setOnClickListener { viewModel.accept() }
    }
}