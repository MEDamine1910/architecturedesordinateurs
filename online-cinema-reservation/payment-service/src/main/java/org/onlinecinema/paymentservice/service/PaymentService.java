package org.onlinecinema.paymentservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.onlinecinema.paymentservice.entities.PaymentRequest;
import org.onlinecinema.paymentservice.entities.PaymentResponse;
import org.onlinecinema.paymentservice.exception.PaymentException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final Random random = new Random();

    public PaymentResponse processPayment(PaymentRequest request) {
        // Validation des données
        validatePaymentRequest(request);

        log.info("Traitement du paiement pour la réservation ID: {}, Montant: {}",
                request.getReservationId(), request.getAmount());

        // Simulation d'un traitement de paiement
        // Dans un vrai système, cela appellerait une API de paiement réelle
        boolean paymentSuccess = simulatePayment(request);

        if (paymentSuccess) {
            log.info("Paiement réussi pour la réservation ID: {}", request.getReservationId());
            return new PaymentResponse(true, "Paiement réussi ! Transaction ID: " + generateTransactionId());
        } else {
            log.warn("Échec du paiement pour la réservation ID: {}", request.getReservationId());
            throw new PaymentException("Échec du paiement. Veuillez vérifier vos informations de paiement.");
        }
    }

    private void validatePaymentRequest(PaymentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La requête de paiement ne peut pas être nulle");
        }
        if (request.getReservationId() == null || request.getReservationId() <= 0) {
            throw new IllegalArgumentException("L'ID de réservation est invalide");
        }
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Le montant doit être supérieur à 0");
        }
        if (request.getCardNumber() == null || request.getCardNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Le numéro de carte est requis");
        }
        if (!isValidCardNumber(request.getCardNumber())) {
            throw new IllegalArgumentException("Le numéro de carte est invalide");
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        // Validation simple : le numéro de carte doit contenir uniquement des chiffres
        // et avoir une longueur entre 13 et 19 caractères
        String cleaned = cardNumber.replaceAll("\\s+", "");
        return cleaned.matches("\\d{13,19}");
    }

    private boolean simulatePayment(PaymentRequest request) {
        // Simulation : 90% de chance de succès
        // Dans un vrai système, cela ferait un appel HTTP vers une API de paiement
        // Pour la démo, on simule un succès si le montant est raisonnable
        if (request.getAmount() > 10000) {
            return false; // Montant trop élevé = échec
        }
        return random.nextDouble() < 0.9; // 90% de succès
    }

    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis() + "-" + random.nextInt(1000);
    }
}
