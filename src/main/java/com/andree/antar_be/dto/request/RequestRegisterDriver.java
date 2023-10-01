package com.andree.antar_be.dto.request;

import com.andree.antar_be.models.Driver;
import com.andree.antar_be.models.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class RequestRegisterDriver {

    @NotEmpty(message = "user id required")
    @Length(min = 36, max = 36)
    String userID;

    @NotEmpty(message = "address is required")
    String address;

    @NotEmpty(message = "date of birth is required")
    String dateOfBirth;

    @NotEmpty(message = "id card picture is required")
    String idCardPicture;

    @NotEmpty(message = "sim image is required")
    String simImage;

    @NotEmpty(message = "transportation type is required")
    @Pattern(regexp = "Mobil|Motor", flags = Pattern.Flag.CASE_INSENSITIVE, message = "value must between motor and mobil")
    String transportationType;

    public Driver toDriver(User user) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date(dateFormat.parse(dateOfBirth).getTime());
        return Driver.builder()
                .user(user)
                .address(this.address)
                .idCardPicture(this.idCardPicture)
                .transportationType(this.transportationType)
                .simImage(this.simImage)
                .dateOfBirth(date)
                .build();
    }
}
