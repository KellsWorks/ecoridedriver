package app.ecoride_agent.ui.trips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import app.ecoride_agent.R;
import app.ecoride_agent.adapters.TabsAdapter;
import app.ecoride_agent.databinding.ActivityTripsBinding;

public class TripsActivity extends AppCompatActivity {

    private ActivityTripsBinding tripsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tripsBinding = DataBindingUtil.setContentView(this, R.layout.activity_trips);

        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        tripsBinding.tripsViewPager.setAdapter(tabsAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        tripsBinding.tripsBack.setOnClickListener(this::navigateUp);
    }

    public void navigateUp(View v){
        this.onBackPressed();
    }
}