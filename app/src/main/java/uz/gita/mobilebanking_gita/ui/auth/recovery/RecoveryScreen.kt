package uz.gita.mobilebanking_gita.ui.auth.recovery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.gita.mobilebanking_gita.R

class RecoveryScreen : Fragment() {

    companion object {
        fun newInstance() = RecoveryScreen()
    }

    private lateinit var viewModel: RecoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recovery_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecoverViewModel::class.java)
        // TODO: Use the ViewModel
    }

}