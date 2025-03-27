package com.bvlabs.bvpay.controller;

import com.bvlabs.bvpay.service.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> request) {
        try {
            Long amount = Long.valueOf(request.get("amount").toString());
            String currency = request.get("currency").toString();
            String clientSecret = stripeService.createPaymentIntent(amount, currency);
            return Map.of("clientSecret", clientSecret);
        } catch (StripeException e) {
            return Map.of("error", e.getMessage());
        }
    }
}
