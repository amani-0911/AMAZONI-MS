package org.sid.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
        private int paymentId;
    private String paymentStatus;
    private String transactionId;
    private int OrderId;
    private double amount;


}
