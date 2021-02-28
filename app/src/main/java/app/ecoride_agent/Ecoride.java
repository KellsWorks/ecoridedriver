package app.ecoride_agent;

import android.app.Application;
import android.location.Location;

import androidx.multidex.MultiDex;

import app.ecoride_agent.utils.ConnectivityReceiver;
import timber.log.Timber;

public class Ecoride extends Application {

    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;
    public static float DEFAULT_ZOOM = 15;
    public static Location mLastKnownLocation;
    public static final int PERMISSIONS_REQUEST_PHONE = 4;
    public static final int PICK_LOCATION_REQUEST_CODE = 3;
    public static final String BASE_URL = "http://192.168.43.200:322/api/ecoride/v1/";

    private static Ecoride mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        MultiDex.install(this);

        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static synchronized Ecoride getInstance() {
        return mInstance;
    }

    public static void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
