package com.inqoo.TavelOfficeWeb.Repository;
import com.inqoo.TavelOfficeWeb.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TripJpaRepository extends JpaRepository<Trip,Integer> {
    List<Trip> findAllByPriceEurBetween(double from, double to);
}