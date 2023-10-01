package com.andree.antar_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "Customer")
@Table(name = "customers")
public class Customer extends Model{

    @Column(nullable = false)
    private Date dateOfBirth;
    @Column(nullable = false)
    private String idCardPicture;
    @Column(nullable = false)
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
