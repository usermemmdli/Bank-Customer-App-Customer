package com.example.Bank_Customer_App_Customer.mapper;

import com.example.Bank_Customer_App_Customer.dao.entity.Customers;
import com.example.Bank_Customer_App_Customer.dto.request.CustomersRequest;
import org.junit.jupiter.api.Test;

public class CustomersMapperTest {
    @Test
    void toCustomersResponse(){
        //Arrange
        var request = new Customers();
        request.setName("Mustafa");
        request.setSurname("Mammadli");
        request.setEmail("<EMAIL>");
        request.setIsActive(true);

        //Act(actual)
        var actual = CustomersMapper.customersToCustomersResponse(request);

        //Assert
        assert actual.getName().equals("Mustafa");
        assert actual.getSurname().equals("Mammadli");
        assert actual.getEmail().equals("<EMAIL>");
        assert actual.getIsActive().equals(true);
    }

    @Test
    void toCustomersEntity(){
        //Arrange
        var request = new CustomersRequest();
        request.setName("Mustafa");
        request.setSurname("Mammadli");
        request.setEmail("<EMAIL>");

        //Act(actual)
        var actual = CustomersMapper.customersRequestToCustomers(request);

        //Assert
        assert actual.getName().equals("Mustafa");
        assert actual.getSurname().equals("Mammadli");
        assert actual.getEmail().equals("<EMAIL>");
    }
}
