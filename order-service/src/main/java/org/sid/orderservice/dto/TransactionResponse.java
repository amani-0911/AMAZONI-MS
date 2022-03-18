package org.sid.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.Payment;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponse {
    private Order order;
    private double amount;
    private String transactionId;
    private String message;

}
