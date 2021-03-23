package app.ecoride_agent.epoxy

import app.ecoride_agent.data.Notifications
import com.airbnb.epoxy.Typed2EpoxyController

class NotificationsEpoxyController : Typed2EpoxyController<Int, List<Notifications>> (){

    override fun buildModels(status: Int?, notifications: List<Notifications>?) {
        if (notifications != null) {
            for(notification in notifications){
                NotificationsEpoxyModel_()
                    .id(notification.title)
                    .data(notification)
                    .click { _, _, _, position ->

                        setData(1, notifications.drop(position))

                    }
                    .addTo(this)
            }
        }
    }

}