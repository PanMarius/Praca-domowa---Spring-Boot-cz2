package com.inqoo.TavelOfficeWeb.Repository;


import com.inqoo.TavelOfficeWeb.Model.Customer;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    private List<Customer> cutomers = new ArrayList<>(); // dane

    public void saveCustmer(Customer customer) {
        cutomers.add(customer);
    } // logikę biznesową

    public List<Customer> getAllCustomers() {
        return cutomers;


    }
}