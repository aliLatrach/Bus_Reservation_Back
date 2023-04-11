package fr.mobiservice.dto;

import java.io.Serializable;

import fr.mobiservice.domain.Bus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusDto implements Serializable {

    private Long busId;
    private int busNumber;

    public static BusDto fromEntity(Bus bus) {
        return new BusDto(bus.getBusId(), bus.getBusNumber());
    }

    public Bus toEntity() {
        return new Bus(this.busId, this.busNumber);
    }

}
