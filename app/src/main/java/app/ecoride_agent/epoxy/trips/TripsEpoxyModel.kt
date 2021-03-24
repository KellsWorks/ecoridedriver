package app.ecoride_agent.epoxy.trips

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import app.ecoride_agent.R
import app.ecoride_agent.data.Trips
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass
abstract class TripsEpoxyModel : EpoxyModelWithHolder<TripsEpoxyModel.TripsEpoxyViewHolder>() {

    @EpoxyAttribute
    var data: Trips? = null

    @EpoxyAttribute
    var click: View.OnClickListener? = null

    override fun createNewHolder(): TripsEpoxyViewHolder {
        return TripsEpoxyViewHolder()
    }

    override fun bind(holder: TripsEpoxyViewHolder) {
        super.bind(holder)

        holder.banner!!.setImageResource(data!!.banner)
        holder.icon!!.setImageResource(data!!.icon)
        holder.title?.text = data!!.title
        holder.content?.text = data!!.content
        holder.time?.text = data!!.date
        holder.close?.setOnClickListener(click)

    }

    override fun getDefaultLayout(): Int {
        return R.layout.model_trips
    }

    class TripsEpoxyViewHolder : EpoxyHolder() {

        var banner: ImageView? = null
        var icon: ImageView? = null
        var title: TextView? = null
        var content: TextView? = null
        var time: TextView? = null
        var close: ImageView? = null

        override fun bindView(itemView: View) {

            banner = itemView.findViewById(R.id.tripBanner)
            icon = itemView.findViewById(R.id.tripIcon)
            title = itemView.findViewById(R.id.tripRoute)
            content = itemView.findViewById(R.id.tripContent)
            close  = itemView.findViewById(R.id.tripClose)

        }

    }
}