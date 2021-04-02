package app.ecoride_agent.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.broadcasts.BroadcastLocation
import app.ecoride_agent.databinding.FragmentOfflineBinding
import app.ecoride_agent.helpers.SharedHelper

open class OfflineFragment : Fragment() {

    private lateinit var offlineBinding: FragmentOfflineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        offlineBinding = FragmentOfflineBinding.inflate(layoutInflater, container, false)
        offlineBinding.lifecycleOwner = this
        offlineBinding.fragment = this

        return offlineBinding.root
    }

    open fun goOffline(v: View?) {
        SharedHelper.putKey(requireContext(), "is_offline", "true")
        val broadcastLocation = BroadcastLocation()
        broadcastLocation.broadcastLocation(
            SharedHelper.getIntKey(context, "user_id"),
            0.0, 0.0
        )
        findNavController().navigateUp()
    }
}