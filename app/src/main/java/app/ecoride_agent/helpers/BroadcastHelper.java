package app.ecoride_agent.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import timber.log.Timber;

public class BroadcastHelper extends AsyncTask<String, Void, String> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;

    public BroadcastHelper(Context context) {
        this.context = context;
    }

    public void startBroadcasting(){

        SharedPreferences sharedPreferences = context.getSharedPreferences("MapsMainLocation", Context.MODE_PRIVATE);

        float latitude = sharedPreferences.getFloat("Latitude", 0F);
        Float longitude = sharedPreferences.getFloat("Longitude", 0F);

        Timber.e(Float.toString(latitude));
    }

    public void stopBroadcasting(){

    }

    @Override
    protected String doInBackground(String... strings) {
        startBroadcasting();
        return null;
    }
}
