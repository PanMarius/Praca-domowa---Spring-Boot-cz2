package com.inqoo.TavelOfficeWeb.Controler;

import com.inqoo.TavelOfficeWeb.Model.Customer;
import com.inqoo.TavelOfficeWeb.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.hibernate.loader.internal.AliasConstantsHelper.get;

@RestController
public class CustomerControler {
    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/customers", produces = "application/json")
    public List<Customer> customers( @RequestParam(name="firstLastNameFragment", required = false) String firstLastNameFragment,
                                    @RequestParam(name="addressFragment", required = false) String addressFragment,
                                    @RequestParam(name="trip", required = false) Boolean trip){
        return customerService.getAllCustomers(firstLastNameFragment,addressFragment,trip);
    }


    @GetMapping(path = "/customers/{customerId}", produces = "application/json")
    public Customer customerById(@PathVariable("customerId") Integer id) {

        return customerService.getAllCustomers( null,null,null).get(id);

    }

    @PostMapping(path = "/customers", consumes = "application/json")
    public ResponseEntity createNewTrip(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        URI savedCustomerId = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        // powinniśmy zwrócić URL właśnie zapisanego miasta
        return ResponseEntity.created(savedCustomerId).build();
    }

}