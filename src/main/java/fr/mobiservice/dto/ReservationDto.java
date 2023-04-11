package fr.mobiservice.dto;

import java.io.Serializable;

import fr.mobiservice.domain.Reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto implements Serializable {

    
    private Long reservationId;

    public static ReservationDto fromEntity(Reservation reservation) {
        return new ReservationDto(reservation.getReservationId());
    }

    public Reservation toEntity() {
        return Reservation.builder()
            .reservationId(this.reservationId).build();
    }

}
