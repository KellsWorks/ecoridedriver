package app.ecoride_agent.adapters


import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import app.ecoride_agent.ui.trips.incoming.IncomingTripsFragment
import app.ecoride_agent.ui.trips.past.PastFragment


@Suppress("DEPRECATION")
@SuppressLint("Deprecated")
class TabsAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val titles = arrayOf("Incoming", "Past")

    override fun getItem(i: Int): Fragment {

        val fragmentUpcoming = IncomingTripsFragment()
        val fragmentPast = PastFragment()

        val fragments = arrayOf(fragmentUpcoming, fragmentPast, fragmentPast)

        return fragments[i]

    }

    override fun getPageTitle(position: Int): CharSequence {

        return titles[position]
    }

    override fun getCount(): Int {

        return 2
    }
}