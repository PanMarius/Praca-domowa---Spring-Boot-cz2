package com.inqoo.TavelOfficeWeb.Controler;

import com.inqoo.TavelOfficeWeb.Service.TripService;
import com.inqoo.TavelOfficeWeb.exception.ErrorMessage;
import com.inqoo.TavelOfficeWeb.exception.NoTripFoundException;
import com.inqoo.TavelOfficeWeb.Trip;
import com.inqoo.TavelOfficeWeb.exception.WrongParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TripController {

    @Autowired
    private TripService tripService;
    @PostMapping(path = "/trips", consumes = "application/json")
    public ResponseEntity createTrip(@RequestBody Trip trip) {
        tripService.saveTrip(trip);

        URI savedCityUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(trip.getId())
                .toUri();
        // powinniśmy zwrócić URL właśnie zapisanego miasta
        return ResponseEntity.created(savedCityUri).build();
    }
    //    @PostMapping(path ="/trips", consumes = "application/json")
//    public ResponseEntity createCity(@RequestBody Trip trip){
//       tripService.saveTrip(trip);
//       return ResponseEntity.created(null).build();
//}
    @GetMapping(path = "/trips", produces = "application/json")
    public List<Trip> trips(@RequestParam(name="tripDestinationFragment", required = false) String nameFragment){
        System.out.println("Zapytanie zawierało parametr 'tripDestinationFragment' o wartości: "+nameFragment);
        return tripService.getAllTrips(nameFragment);
    }
    @GetMapping(path = "/tripsByPrice", produces = "application/json")
    public List<Trip> tripsByPrice(@RequestParam double priceFrom, @RequestParam double priceTo) {
        try {
            return tripService.getByPrice(priceFrom, priceTo);
        } catch (NoTripFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping(path = "/trips/{tripId}", produces = "application/json")
    public Trip tripsById(@PathVariable("tripId") Integer id){
        return tripService.getAllTrips(null).get(id);
    }
    @ExceptionHandler(NoTripFoundException.class) // jaki wyjątek obsługujemy
    @ResponseStatus(HttpStatus.NOT_FOUND) // jaki kod HTTP zwrócimy
    public ResponseEntity<ErrorMessage> handleNoTripFoundException(NoTripFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body( new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
    @ExceptionHandler(WrongParameters.class) // jaki wyjątek obsługujemy
    @ResponseStatus(HttpStatus.BAD_REQUEST) // jaki kod HTTP zwrócimy
    public ResponseEntity<ErrorMessage> handleBadParamerersException(WrongParameters exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body( new ErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
    @DeleteMapping(path = "/trips/{id}")
    public void removeTrip(@PathVariable Integer id) {
        tripService.removeTripById(id);
    }
    @PutMapping(path = "/trips/{id}")
    public ResponseEntity updateTrip(@PathVariable Integer id, @RequestBody Trip trip) {
        tripService.updateTrip(id, trip);
        return ResponseEntity.noContent().build();
    }
}