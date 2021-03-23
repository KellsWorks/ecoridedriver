package app.ecoride_agent.ui.onboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import app.ecoride_agent.databinding.FragmentSecondSliderBinding;

public class SecondSlider extends Fragment {

    FragmentSecondSliderBinding fragmentSecondSliderBinding;

    public SecondSlider() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentSecondSliderBinding = FragmentSecondSliderBinding.inflate(inflater, container, false);
        fragmentSecondSliderBinding.setLifecycleOwner(this);

        return fragmentSecondSliderBinding.getRoot();
    }
}