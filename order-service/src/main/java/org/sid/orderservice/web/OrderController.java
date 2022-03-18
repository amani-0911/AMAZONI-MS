package org.sid.orderservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.sid.orderservice.dto.TransactionRequest;
import org.sid.orderservice.dto.TransactionResponse;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.Payment;
import org.sid.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {
         return orderService.saveOrder(transactionRequest);
    }
}
