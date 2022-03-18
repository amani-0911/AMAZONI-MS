package org.sid.paymentservice.repository;

import org.sid.paymentservice.entiries.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List<Payment> findByOrderId(int orderId);

        @Transactional
        @Query("update Payment p set p.paymentStatus=:status where p.paymentId=:paymentId")
        int updateStatus(@Param("paymentId") int paymentId,@Param("status") String status);
}
