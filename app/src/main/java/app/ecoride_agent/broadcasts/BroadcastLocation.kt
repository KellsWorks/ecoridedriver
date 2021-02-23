package app.ecoride_agent.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class BroadcastLocation : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.e("Changed")
    }
}