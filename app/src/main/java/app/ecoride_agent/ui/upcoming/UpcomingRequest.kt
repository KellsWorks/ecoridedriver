package app.ecoride_agent.ui.upcoming

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.ecoride_agent.R
import app.ecoride_agent.databinding.FragmentUpcomingRequestBinding

open class UpcomingRequest : Fragment() {

    private lateinit var mPlayer: MediaPlayer

    private lateinit var upcomingRequestBinding: FragmentUpcomingRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPlayer = MediaPlayer.create(requireActivity(), R.raw.alert_tone)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        upcomingRequestBinding = FragmentUpcomingRequestBinding.inflate(
            inflater, container, false
        )
        upcomingRequestBinding.lifecycleOwner = this

        return upcomingRequestBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPlayer.start()
        mPlayer.isLooping
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        upcomingRequestBinding.btnReject.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onStop() {
        super.onStop()
        stopMediaPlayer()
    }

    private fun stopMediaPlayer() {
        mPlayer.stop()
        mPlayer.release()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopMediaPlayer()
    }

}