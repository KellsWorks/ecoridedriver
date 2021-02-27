package app.ecoride_agent.ui.upcoming;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.ecoride_agent.R;


public class UpcomingRequest extends Fragment {

    Context context;
    CountDownTimer countDownTimer;

    Integer arrivalTime = 0;
    MediaPlayer mPlayer;

    public UpcomingRequest() {
        mPlayer = MediaPlayer.create(requireActivity(), R.raw.alert_tone);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_upcoming_request, container, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        stopMediaPlayer();
    }

    void stopMediaPlayer() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopMediaPlayer();
    }

}