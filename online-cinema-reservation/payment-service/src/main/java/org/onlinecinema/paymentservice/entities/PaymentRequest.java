package org.onlinecinema.paymentservice.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull(message = "L'ID de réservation est requis")
    private Long reservationId;

    @NotNull(message = "Le montant est requis")
    @Min(value = 0, message = "Le montant doit être positif")
    private Double amount;

    @NotBlank(message = "Le numéro de carte est requis")
    private String cardNumber;
}
