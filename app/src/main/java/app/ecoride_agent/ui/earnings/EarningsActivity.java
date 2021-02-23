package app.ecoride_agent.ui.earnings;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import app.ecoride_agent.R;
import app.ecoride_agent.databinding.ActivityEarningsBinding;

public class EarningsActivity extends AppCompatActivity {

    private ActivityEarningsBinding earningsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        earningsBinding = DataBindingUtil.setContentView(this, R.layout.activity_earnings);
    }

    @Override
    protected void onStart() {
        super.onStart();

        earningsBinding.earningsBack.setOnClickListener(this::navigateUp);
    }

    public void navigateUp(View v)
    {
        this.onBackPressed();
    }
}