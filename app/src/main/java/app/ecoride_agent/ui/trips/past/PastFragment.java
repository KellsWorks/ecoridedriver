package app.ecoride_agent.ui.trips.past;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import app.ecoride_agent.R;
import app.ecoride_agent.data.Trips;
import app.ecoride_agent.databinding.FragmentPastBinding;
import app.ecoride_agent.epoxy.trips.TripsEpoxyController;

public class PastFragment extends Fragment {

    FragmentPastBinding fragmentPastBinding;

    TripsEpoxyController tripsEpoxyController;

    public PastFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tripsEpoxyController = new TripsEpoxyController();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       fragmentPastBinding = FragmentPastBinding.inflate(inflater, container, false);
       fragmentPastBinding.setLifecycleOwner(this);

       return fragmentPastBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Trips> tripsArrayList = new ArrayList<>();

        tripsArrayList.add(new Trips(R.drawable.rectangle_green, R.drawable.ic_baseline_check_circle, "Ginnery corner - Sanjika palace", "2KM Drive - MK2,000", "21st March, 2021"));

        setUpRecycler(tripsArrayList);
    }

    public void setUpRecycler(List<Trips> data){
        tripsEpoxyController.setData(1, data);
        fragmentPastBinding.pastRecycler.setController(tripsEpoxyController);
    }
}