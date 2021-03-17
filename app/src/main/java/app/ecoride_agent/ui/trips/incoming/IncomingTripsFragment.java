package app.ecoride_agent.ui.trips.incoming;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.ecoride_agent.R;


public class IncomingTripsFragment extends Fragment {



    public IncomingTripsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_incoming_trips, container, false);
    }
}