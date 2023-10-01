package com.andree.antar_be.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Order")
@Table(name = "orders")
public class Order extends Model{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            nullable = false
    )
    private User customer;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "driver_id",
            referencedColumnName = "id",
            nullable = true,
            foreignKey = @ForeignKey(
                    name = "driver_order_id_fk"
            )
    )
    private User driver;

    private boolean isDone;

    private String locationLat;
    private String locationLng;
    private String locationName;

    private String destinationLat;
    private String destinationLng;
    private String destinationName;

    double totalDistance;
    BigInteger amount;
}
