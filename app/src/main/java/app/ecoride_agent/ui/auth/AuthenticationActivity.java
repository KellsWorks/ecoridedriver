package app.ecoride_agent.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import app.ecoride_agent.MainActivity;
import app.ecoride_agent.R;
import app.ecoride_agent.databinding.ActivityAuthenticationBinding;
import app.ecoride_agent.helpers.SharedHelper;

public class AuthenticationActivity extends AppCompatActivity {

    ActivityAuthenticationBinding authenticationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authenticationBinding = DataBindingUtil.setContentView(this, R.layout.activity_authentication);
    }

}