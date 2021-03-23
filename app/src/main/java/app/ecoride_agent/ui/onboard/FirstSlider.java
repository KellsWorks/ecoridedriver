package app.ecoride_agent.ui.onboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import app.ecoride_agent.databinding.FragmentFirstSliderBinding;

public class FirstSlider extends Fragment {

    FragmentFirstSliderBinding fragmentFirstSliderBinding;

    public FirstSlider() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentFirstSliderBinding = FragmentFirstSliderBinding.inflate(inflater, container, false);
        fragmentFirstSliderBinding.setLifecycleOwner(this);

        return fragmentFirstSliderBinding.getRoot();
    }
}