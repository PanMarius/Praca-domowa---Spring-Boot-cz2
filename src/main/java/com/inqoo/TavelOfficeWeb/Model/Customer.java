package com.inqoo.TavelOfficeWeb.Model;

import com.inqoo.TavelOfficeWeb.Trip;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode

@Builder @NoArgsConstructor  @AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private List<Trip> trips;


        @Embedded
        @AttributeOverrides({
                @AttributeOverride(name = "country", column = @Column(name = "country")),
                @AttributeOverride(name = "province", column = @Column(name = "province")),
                @AttributeOverride(name = "road", column = @Column(name = "road")),
                @AttributeOverride(name = "houseNumber", column = @Column(name = "house_number")),
                @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code"))
        })
        private CustomerAddressDetails customerAddressDetails;


        @Embedded
        /// lub @AttributeOverrides( value= {
        @AttributeOverrides({
                @AttributeOverride(name = "firstname", column = @Column(name = "first_name")),
                @AttributeOverride(name = "lastname", column = @Column(name = "last_name")),
                @AttributeOverride(name = "age", column = @Column(name = "age")),
                @AttributeOverride(name = "dateOfBirth", column = @Column(name = "date_of_birth")),
                @AttributeOverride(name = "pesel", column = @Column(name = "pesel")),
                @AttributeOverride(name = "phone", column = @Column(name = "contact_phone"))
        })
        private CustomerNameDetails customerNameDetails;
    }
