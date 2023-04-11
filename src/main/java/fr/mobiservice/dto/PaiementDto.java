package fr.mobiservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaiementDto {

    private Long reservationId;
    private MoyenPaiementDto moyenPaiement;

}
