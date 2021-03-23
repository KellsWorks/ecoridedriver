package app.ecoride_agent.epoxy.chats


import androidx.navigation.findNavController
import app.ecoride_agent.R
import app.ecoride_agent.data.Messages
import com.airbnb.epoxy.Typed2EpoxyController

class MessagesEpoxyController : Typed2EpoxyController<Boolean, List<Messages>>() {

    override fun buildModels(status: Boolean?, messages: List<Messages>?) {
        if (messages != null) {
            for (message in messages){
                MessagesEpoxyModel_()
                    .id(message.name)
                    .data(message)
                    .click { _, parentView, _, _ ->
                        parentView.icon?.findNavController()?.navigate(R.id.action_chatsFragment_to_singleChatFragment)
                    }
                    .addTo(this)
            }
        }
    }
}
