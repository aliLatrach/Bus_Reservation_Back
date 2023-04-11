package fr.mobiservice.dto;

import fr.mobiservice.domain.Facture;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FactureDto {
    private Long factureId;
    private Long reservationId;

    public static FactureDto fromEntity(Facture facture) {
        return FactureDto.builder()
            .factureId(facture.getFactureId())
            .reservationId(facture.getReservation().getReservationId()).build();
    }
}
