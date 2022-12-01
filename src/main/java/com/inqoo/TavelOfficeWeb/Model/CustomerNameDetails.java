package com.inqoo.TavelOfficeWeb.Model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.time.LocalDate;
@Data
@Embeddable
public class CustomerNameDetails  {
    public  String firstname;
    private String lastname;
    private int age;
    private LocalDate dateOfBirth;
    private String pesel;
    private String phone;

//    public void fnLn(){
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(Firstname);
//        stringBuilder.append(" ");
//        stringBuilder.append(Lastname);
//        String personalName = stringBuilder.toString();
//    }

}
