package app.ecoride_agent.epoxy.chats

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import app.ecoride_agent.R
import app.ecoride_agent.data.Messages
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass
abstract class MessagesEpoxyModel : EpoxyModelWithHolder<MessagesEpoxyModel.MessagesEpoxyViewHolder>() {

    @EpoxyAttribute
    var data: Messages? = null

    @EpoxyAttribute
    var click: View.OnClickListener? = null

    override fun createNewHolder(): MessagesEpoxyViewHolder {
        return MessagesEpoxyViewHolder()
    }

    override fun bind(holder: MessagesEpoxyViewHolder) {
        super.bind(holder)

        holder.name!!.text = data!!.name
        holder.title!!.text = data!!.title
        holder.icon?.setImageResource(data!!.image)
        holder.status?.setImageResource(data!!.status)

        holder.icon?.setOnClickListener(click)

    }

    override fun getDefaultLayout(): Int {
        return R.layout.model_messages
    }

    class MessagesEpoxyViewHolder : EpoxyHolder() {

        var icon: de.hdodenhof.circleimageview.CircleImageView? = null
        var name: TextView? = null
        var title: TextView? = null
        var status: ImageView? = null

        override fun bindView(itemView: View) {

            icon = itemView.findViewById(R.id.messagesIcon)
            name = itemView.findViewById(R.id.messagesName)
            title = itemView.findViewById(R.id.messagesTitle)
            status = itemView.findViewById(R.id.messagesStatus)

        }

    }
}