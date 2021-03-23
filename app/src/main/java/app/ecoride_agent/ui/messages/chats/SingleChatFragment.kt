package app.ecoride_agent.ui.messages.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import app.ecoride_agent.databinding.FragmentSingleChatBinding


class SingleChatFragment : Fragment() {

    private lateinit var singleChatBinding : FragmentSingleChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        singleChatBinding = FragmentSingleChatBinding.inflate(inflater , container , false)
        singleChatBinding.lifecycleOwner = this
        singleChatBinding.frament = this

        return singleChatBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun onBackPressed(view: View){
        view.findNavController().navigateUp()
    }
}