package com.andree.antar_be.dto.request;

import com.andree.antar_be.models.Customer;
import com.andree.antar_be.models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class RequestRegisterCustomer {

    @NotEmpty(message = "user id required")
    @Length(min = 36, max = 36)
    String userID;
    @NotEmpty(message = "Date birth must field")
    String dateOfBirth;

    @NotEmpty(message = "id card is required")
    String idCardPicture;

    @NotEmpty(message = "address is required")
    String address;

    public Customer toCustomer(User user) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date(dateFormat.parse(dateOfBirth).getTime());
        return Customer.builder()
                .user(user)
                .address(this.address)
                .dateOfBirth(date)
                .idCardPicture(this.idCardPicture)
                .build();
    }

}
