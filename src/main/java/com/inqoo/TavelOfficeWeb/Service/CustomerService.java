package com.inqoo.TavelOfficeWeb.Service;

import com.inqoo.TavelOfficeWeb.Model.Customer;
import com.inqoo.TavelOfficeWeb.Model.CustomerNameDetails;
import com.inqoo.TavelOfficeWeb.Repository.CustomerJpaRepository;
import com.inqoo.TavelOfficeWeb.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerJpaRepository customerJpaRepository;
    private CustomerNameDetails customerNameDetails;

    public void saveCustomer(Customer customer) {
        customerJpaRepository.save(customer);
    } // logikę biznesową


    public List<Customer> getAllCustomers(String firstLastNameFragment, String addressFragment, Boolean withoutAnyTrip) {
///////////////////////////////////////////tu trzeba zmian !!!!!!!!
        Customer exampleCustomer = Customer.builder()
                .
                //.
                //.customerNameDetails.Firstname
                //.address(addressFragment)
                .build();

        ExampleMatcher firstLastNameFragmentMatcher = ExampleMatcher
                .matchingAll()
                .withMatcher("address",ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("Firstname", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        List<Customer> result = customerJpaRepository.findAll(Example.of(exampleCustomer, firstLastNameFragmentMatcher));
        if (withoutAnyTrip != null) {
            if (withoutAnyTrip) {
                result = result.stream()
                        .filter(e -> e.getTrips() == null || e.getTrips().isEmpty())
                        .collect(Collectors.toList());
            } else if (!withoutAnyTrip) {
                result = result.stream()
                        .filter(e -> e.getTrips() != null && !e.getTrips().isEmpty())
                        .collect(Collectors.toList());
            }
        }
        return result;

    }

}



