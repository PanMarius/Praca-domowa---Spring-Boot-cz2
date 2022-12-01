package com.inqoo.TavelOfficeWeb.Service;

import com.inqoo.TavelOfficeWeb.Repository.CustomerJpaRepository;
import com.inqoo.TavelOfficeWeb.Repository.TripJpaRepository;
import com.inqoo.TavelOfficeWeb.exception.NoTripFoundException;
import com.inqoo.TavelOfficeWeb.Trip;
import com.inqoo.TavelOfficeWeb.exception.WrongParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripService {


        @Autowired
        private TripJpaRepository tripJpaRepository;
        @Autowired
        private CustomerJpaRepository customerJpaRepository;

        public void saveTrip(Trip trip) {
                tripJpaRepository.save(trip);
        } // logikę biznesową
        public void removeTripById(Integer id) {
                tripJpaRepository.deleteById(id);
        }
        public void updateTrip(Integer id, Trip tripToUpdate) {
                if (id != tripToUpdate.getId()){
                        throw new RuntimeException("Bad request");
                }
                Optional<Trip> maybeTrip = tripJpaRepository.findById(id);
                if (maybeTrip.isEmpty()) { // pobieramy wg id żeby upewnić się, że to będzie aktualizacja
                        // wyrzucenie wyjątku !
                        return;
                }
                tripJpaRepository.save(tripToUpdate);
        }
        public List<Trip> getAllTrips() {
                return tripJpaRepository.findAll();
        }

        public List<Trip> getAllTrips(String nameFragment) {
                List<Trip> result = tripJpaRepository.findAll();
                if (nameFragment != null) {
                        result = result.stream()
                                .filter(c -> c.getDestination().contains(nameFragment))
                                .collect(Collectors.toList());
                }
                return result;
        }
        public List<Trip> getByPrice(double priceFrom, double priceTo) throws NoTripFoundException, WrongParameters {
                if (priceFrom>priceTo){
                        throw new WrongParameters("Wrong input data  "+priceFrom+" and "+priceTo);
                }
                List<Trip> tripsByPrice = tripJpaRepository.findAllByPriceEurBetween(priceFrom, priceTo);

                if (tripsByPrice.isEmpty()) {
                        throw new NoTripFoundException("No Trip with price between "+priceFrom+" and "+priceTo);
                }
                return tripsByPrice;
        }


}