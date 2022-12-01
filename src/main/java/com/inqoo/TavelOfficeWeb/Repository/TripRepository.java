package com.inqoo.TavelOfficeWeb.Repository;


import com.inqoo.TavelOfficeWeb.Trip;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Repository
public class TripRepository {
    private List<Trip> trips = new ArrayList<>(); // dane

    public void saveTrip(Trip trip) {
        trips.add(trip);
    } // logikę biznesową
    public List<Trip> findTripsByPrice(double priceFrom, double priceTo) {
        return trips.stream()
                .filter( c -> c.getPriceEur() > priceFrom)
                .filter( c -> c.getPriceEur() < priceTo)
                .collect(Collectors.toList());
    }
    public List<Trip> getAllTrips() {
        return trips;
    }
    @PostConstruct
    // metoda oznaczona tą adnotacją wykona się po utworzeniu komponentu, a przed wywołaniem dowolnej metody biznesowej
    public void createData() {
        Trip c1 = new Trip();
        c1.setDestination("Oslo");
        c1.setPriceEur(500);
        Trip c2 = new Trip();
        c2.setDestination("Łódź");
        c2.setPriceEur(200);
        Trip c3 = new Trip();
        c3.setDestination("Mediolan") ;
        c3.setPriceEur(400);
        Trip c4 = new Trip();
        c4.setDestination("Paryż") ;
        c4.setPriceEur(770);
        trips.add(c1);
        trips.add(c2);
        trips.add(c3);
        trips.add(c4);
    }
}