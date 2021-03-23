package app.ecoride_agent.epoxy

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import app.ecoride_agent.R
import app.ecoride_agent.data.Notifications
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass
abstract class NotificationsEpoxyModel : EpoxyModelWithHolder<NotificationsEpoxyModel.NotificationsEpoxyViewHolder>() {

    @EpoxyAttribute
    var data: Notifications? = null

    @EpoxyAttribute
    var click: View.OnClickListener? = null

    override fun createNewHolder(): NotificationsEpoxyViewHolder {
        return NotificationsEpoxyViewHolder()
    }

    override fun bind(holder: NotificationsEpoxyViewHolder) {
        super.bind(holder)

        holder.banner!!.setImageResource(data!!.banner)
        holder.icon!!.setImageResource(data!!.icon)
        holder.title?.text = data!!.title
        holder.content?.text = data!!.content

        holder.close?.setOnClickListener(click)

    }

    override fun getDefaultLayout(): Int {
        return R.layout.model_notification
    }

    class NotificationsEpoxyViewHolder : EpoxyHolder() {

        var banner: ImageView? = null
        var icon: ImageView? = null
        var title: TextView? = null
        var content: TextView? = null
        var close: ImageView? = null

        override fun bindView(itemView: View) {

            banner = itemView.findViewById(R.id.notifyBanner)
            icon = itemView.findViewById(R.id.notifyIcon)
            title = itemView.findViewById(R.id.notifyTitle)
            content = itemView.findViewById(R.id.notifyContent)
            close  = itemView.findViewById(R.id.notifyClose)

        }

    }
}