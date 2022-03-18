package org.sid.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ORDER_TB")
public class Order {
    @Id
   private int id;
    private String name;
    private int qty;
    private double price;
}
