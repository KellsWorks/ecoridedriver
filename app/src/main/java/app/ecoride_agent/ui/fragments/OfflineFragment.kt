package app.ecoride_agent.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.databinding.FragmentOfflineBinding

class OfflineFragment : Fragment() {

    private lateinit var offlineBinding: FragmentOfflineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        offlineBinding = FragmentOfflineBinding.inflate(layoutInflater, container, false)
        offlineBinding.lifecycleOwner = this

        return offlineBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        offlineBinding.goOnlineBtn.setOnClickListener {
            goOffline(it)
        }
    }

    private fun goOffline(v: View?) {
        findNavController().navigateUp()
    }
}