package app.ecoride_agent.ui.documents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import app.ecoride_agent.R;
import app.ecoride_agent.databinding.ActivityDocumentsBinding;

public class DocumentsActivity extends AppCompatActivity {

    private ActivityDocumentsBinding documentsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        documentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_documents);
    }

    @Override
    protected void onStart() {
        super.onStart();

        documentsBinding.dpcumentsBack.setOnClickListener(this::navigateUp);
    }

    public void navigateUp(View view){
        this.onBackPressed();
    }
}