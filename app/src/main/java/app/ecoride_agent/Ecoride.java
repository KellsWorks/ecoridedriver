package app.ecoride_agent;

import android.app.Application;
import android.location.Location;

import androidx.multidex.MultiDex;

import com.google.firebase.messaging.FirebaseMessaging;

import app.ecoride_agent.helpers.SharedHelper;
import app.ecoride_agent.utils.ConnectivityReceiver;
import app.ecoride_agent.network.fcm.FCMTopic;
import timber.log.Timber;

public class Ecoride extends Application {

    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    public static float DEFAULT_ZOOM = 15;
    public static Location mLastKnownLocation;
    public static final String BASE_URL = "http://192.168.43.200:322/api/ecoride/v1/";
    public static final String PROFILES_URL = "http://192.168.43.200:322/storage/profile/";

    public static final String FCM_URL = "https://fcm.googleapis.com/";

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
