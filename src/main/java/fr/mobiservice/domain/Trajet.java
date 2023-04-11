package fr.mobiservice.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trajetId;

    @Column
    private int seatsNumber;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDate;

    @Column
    private float price;

    @Column
    private float discountPrice;

    @Column
    private boolean isDiscount;

    @OneToOne
    @JoinColumn(name = "bus_id_fk")
    private Bus bus;

    public String toString() {
        StringBuffer result = new StringBuffer();
        result
            .append("Bus n°")
            .append(bus.getBusNumber())
            .append(" - trajet ")
            .append(departureDate);
        return result.toString();
    }
}
