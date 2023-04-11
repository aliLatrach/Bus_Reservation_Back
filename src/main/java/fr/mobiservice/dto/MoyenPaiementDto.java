package fr.mobiservice.dto;

import fr.mobiservice.model.MoyenPaiement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoyenPaiementDto {

    private String cardNumber;
    private String expirationDate;
    private String email;

    public MoyenPaiement toEntity() {
        return MoyenPaiement.builder()
            .cardNumber(cardNumber)
            .email(email)
            .expirationDate(expirationDate)
            .build();
    }
}
