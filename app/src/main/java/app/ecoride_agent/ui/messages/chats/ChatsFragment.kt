package app.ecoride_agent.ui.messages.chats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ecoride_agent.R
import app.ecoride_agent.data.Messages
import app.ecoride_agent.databinding.FragmentChatsBinding
import app.ecoride_agent.epoxy.chats.MessagesEpoxyController


class ChatsFragment : Fragment() {

    private lateinit var chatsBinding: FragmentChatsBinding

    private lateinit var controller: MessagesEpoxyController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        chatsBinding = FragmentChatsBinding.inflate(inflater, container,false)
        chatsBinding.lifecycleOwner = this

        return chatsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatsBinding.messagesBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        setUpMessages()
    }

    private fun setUpMessages(){

        controller = MessagesEpoxyController()

        val arrayList = ArrayList<Messages>()
        arrayList.add(Messages(R.drawable.one, "Driver 1", "Where should l..?", R.drawable.circle_green))
        arrayList.add(Messages(R.drawable.two, "Driver 2", "Where should l..?", R.drawable.circle_red))
        arrayList.add(Messages(R.drawable.three, "Driver 3", "Where should l..?", R.drawable.circle_green))
        arrayList.add(Messages(R.drawable.three, "Driver 4", "Where should l..?", R.drawable.circle_red))
        arrayList.add(Messages(R.drawable.one, "Driver 5", "Where should l..?", R.drawable.circle_red))
        arrayList.add(Messages(R.drawable.two, "Driver 6", "Where should l..?", R.drawable.circle_green))
        arrayList.add(Messages(R.drawable.two, "Driver 7", "Where should l..?", R.drawable.circle_red))

        controller.setData(false, arrayList)

        chatsBinding.messagesRecycler.setItemSpacingDp(20)
        chatsBinding.messagesRecycler.setController(controller)
    }
}