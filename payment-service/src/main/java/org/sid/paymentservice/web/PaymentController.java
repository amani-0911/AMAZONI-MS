package org.sid.paymentservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.sid.paymentservice.entiries.Payment;
import org.sid.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return  paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public List<Payment> getAllPaiments(@PathVariable int orderId ) throws JsonProcessingException {
        return paymentService.getAllPaimentsOrder(orderId);
    }


    @GetMapping("/py/{paymentId}")
    public Payment getPayment(@PathVariable int paymentId)  {
        return paymentService.getPayment(paymentId);
    }

    @PutMapping("/py")
    public Payment updatePayment(@RequestBody Payment payment){
        return paymentService.updateStatus(payment);
    }

    @DeleteMapping("/py/{paymentId}")
    public String deletepayment(@PathVariable int paymentId){
        return paymentService.deletePayment(paymentId);
    }
}
