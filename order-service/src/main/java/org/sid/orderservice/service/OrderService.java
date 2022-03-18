package org.sid.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sid.orderservice.dto.TransactionRequest;
import org.sid.orderservice.dto.TransactionResponse;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.Payment;
import org.sid.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {
    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    @Lazy
    private  RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;
    private Logger log= LoggerFactory.getLogger(OrderService.class);
    public TransactionResponse saveOrder(TransactionRequest transactionRequest) throws JsonProcessingException {
     String msg="";
        Order order=transactionRequest.getOrder();
        Payment payment=transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        log.info("orderService  request:{}",new ObjectMapper().writeValueAsString(transactionRequest));

        //rest call
     Payment paymentresponse=restTemplate.postForObject(ENDPOINT_URL,payment,Payment.class);


        log.info("Payment-service response from orderService  rest call response:{}",new ObjectMapper().writeValueAsString(paymentresponse));

  msg= paymentresponse.getPaymentStatus().equals("success")?"payment processiong succesful and order placed":"there is a failure in payment api, order added to cart";

     Order ordersave=   orderRepository.save(order);
       TransactionResponse response= new TransactionResponse();

       response.setOrder(ordersave);
       response.setTransactionId(paymentresponse.getTransactionId());
       response.setAmount(ordersave.getPrice());
       response.setMessage(msg);
       return response;
    }
}
