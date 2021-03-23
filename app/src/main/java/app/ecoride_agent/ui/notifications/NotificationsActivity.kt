package app.ecoride_agent.ui.notifications

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import app.ecoride_agent.R
import app.ecoride_agent.data.Notifications
import app.ecoride_agent.databinding.ActivityNotificationsBinding
import app.ecoride_agent.epoxy.NotificationsEpoxyController


class NotificationsActivity : AppCompatActivity() {

    private lateinit var notificationsBinding: ActivityNotificationsBinding

    private lateinit var controller : NotificationsEpoxyController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationsBinding = DataBindingUtil.setContentView(this@NotificationsActivity, R.layout.activity_notifications)

        notificationsBinding.notificationsBack.setOnClickListener {
            this@NotificationsActivity.onBackPressed()
        }

        controller = NotificationsEpoxyController()

    }

    override fun onStart() {
        super.onStart()

        val arrayList = ArrayList<Notifications>()

        arrayList.add(Notifications(R.drawable.rectangle_blue, R.drawable.ic_baseline_info, "Oops, something went wrong", "Nearest driver could not be found"))
        arrayList.add(Notifications(R.drawable.rectangle_green, R.drawable.ic_baseline_check_circle, "Oops, something went wrong", "Nearest driver could not be found"))
        arrayList.add(Notifications(R.drawable.rectangle_orange, R.drawable.ic_baseline_error, "Oops, something went wrong", "Nearest driver could not be found"))
        arrayList.add(Notifications(R.drawable.rectangle_blue, R.drawable.ic_baseline_info, "Oops, something went wrong", "Nearest driver could not be found"))

        controller.setData(1, arrayList)

        notificationsBinding.notificationsReycler.apply {
            setController(controller)
            setItemSpacingDp(20)
        }

    }

}