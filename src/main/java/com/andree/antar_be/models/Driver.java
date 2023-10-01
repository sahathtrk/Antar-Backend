package com.andree.antar_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "drivers")
@Entity(name = "Driver")
public class Driver extends Model{

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String idCardPicture;
    @Column(nullable = false)
    private String simImage;
    @Column(nullable = false)
    private String transportationType;

    @Column(nullable = false)
    private Date dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
