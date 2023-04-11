package fr.mobiservice.dto;

import java.time.LocalDateTime;

import fr.mobiservice.domain.Trajet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrajetDto {
    
    private Long trajetId;
    private int seatsNumber;
    private LocalDateTime departureDate;
    private float price;
    private BusDto bus;
    private boolean isDiscount;
    private float discountPrice;

    public static TrajetDto fromEntity(Trajet trajet) {
        return TrajetDto.builder()
            .trajetId(trajet.getTrajetId())
            .seatsNumber(trajet.getSeatsNumber())
            .departureDate(trajet.getDepartureDate())
            .price(trajet.getPrice())
            .bus(BusDto.fromEntity(trajet.getBus()))
            .isDiscount(trajet.isDiscount())
            .discountPrice(trajet.getDiscountPrice()).build();
    }
}