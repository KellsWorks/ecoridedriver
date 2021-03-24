package app.ecoride_agent.epoxy.trips

import app.ecoride_agent.data.Trips
import com.airbnb.epoxy.Typed2EpoxyController

class TripsEpoxyController : Typed2EpoxyController<Int, List<Trips>> (){

    override fun buildModels(status: Int?, trips: List<Trips>?) {
        if (trips != null) {
            for(trip in trips){
                TripsEpoxyModel_()
                    .id(trip.title)
                    .data(trip)
                    .click { _, _, _, _ ->

                    }
                    .addTo(this)
            }
        }
    }

}