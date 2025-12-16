package org.onlinecinema.paymentservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.onlinecinema.paymentservice.entities.PaymentRequest;
import org.onlinecinema.paymentservice.entities.PaymentResponse;
import org.onlinecinema.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> pay(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }
}
