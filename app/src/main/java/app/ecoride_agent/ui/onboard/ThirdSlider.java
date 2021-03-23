package app.ecoride_agent.ui.onboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import app.ecoride_agent.databinding.FragmentThirdSliderBinding;


public class ThirdSlider extends Fragment {

    FragmentThirdSliderBinding fragmentThirdSliderBinding;

    public ThirdSlider() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentThirdSliderBinding = FragmentThirdSliderBinding.inflate(inflater, container, false);
        fragmentThirdSliderBinding.setLifecycleOwner(this);

        return fragmentThirdSliderBinding.getRoot();
    }

}