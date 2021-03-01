package app.ecoride_agent.ui.upcoming

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.ecoride_agent.R

open class UpcomingRequest : Fragment() {

    private lateinit var mPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPlayer = MediaPlayer.create(requireActivity(), R.raw.alert_tone)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming_request, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPlayer.start()
        mPlayer.isLooping
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