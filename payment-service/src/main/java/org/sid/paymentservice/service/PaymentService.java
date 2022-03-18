package org.sid.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.sid.paymentservice.entiries.Payment;
import org.sid.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service

public class PaymentService {
    @Autowired
    private  PaymentRepository paymentRepository;

    private Logger log= LoggerFactory.getLogger(PaymentService.class);

    public Payment doPayment(Payment payment) throws JsonProcessingException {
     payment.setPaymentStatus(paymentRondomStatus());
    payment.setTransactionId(UUID.randomUUID().toString());
        log.info("paymentService  request:{}",new ObjectMapper().writeValueAsString(payment));

      return paymentRepository.save(payment);
    }

    public String paymentRondomStatus(){
        return new Random().nextBoolean()?"success":"false";
    }


    public List<Payment> getAllPaimentsOrder(int orderId) throws JsonProcessingException {

       List<Payment> payments=paymentRepository.findByOrderId(orderId);
        log.info("paymentService  getAllPaimentsOrder orderId: {} =>{}",orderId,new ObjectMapper().writeValueAsString(payments));

        return payments;
    }

    @Cacheable(cacheNames = "payments", key = "#paymentId")
    public Payment getPayment(int paymentId)  {
        log.info("fetching data payment from db");
        Optional<Payment> payment = paymentRepository.findById(paymentId);
       if (payment.isPresent()){
           return payment.get();
       }else {
           return new Payment();
       }

    }

    @CachePut(cacheNames = "payments",key = "#payment.orderId")
    public Payment updateStatus(Payment payment){
        paymentRepository.updateStatus(payment.getPaymentId(),payment.getPaymentStatus());
        log.info("payment {} updated with status{}",payment.getPaymentId(),payment.getPaymentStatus());
         return payment;
    }

    @CacheEvict(cacheNames = "payments",key = "#paymentId")
    public String deletePayment(int paymentId){
           paymentRepository.deleteById(paymentId);
           return "payment deleted";
    }


}
