package com.bvlabs.bvpay.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StripeService {

    public String createPaymentIntent(Long amount, String currency) throws StripeException {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount) // Valor em centavos (ex: 1000 = R$10,00)
                        .setCurrency(currency) // "brl" para reais
                        //.addAllPaymentMethodType(List.of("card", "pix")) // Pix somente apos 60 dias de conta ativa processando pagamentos
                        .addPaymentMethodType("card")
                        .build();

        PaymentIntent intent = PaymentIntent.create(params);
        System.out.println("PAYMENT INTENT: " + intent.toString());

        return intent.getClientSecret(); // Retorna um identificador para o frontend
    }
}
