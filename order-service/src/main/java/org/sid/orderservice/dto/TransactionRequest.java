package org.sid.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.Payment;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {
    private Order order;
    private Payment payment;
}
