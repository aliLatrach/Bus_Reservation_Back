package fr.mobiservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Billet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billetId;

    @Column
    private int quantity;

    @OneToOne
    @JoinColumn(name = "trajet_id_fk")
    private Trajet trajet;

    @ManyToOne
    @JoinColumn(name = "reservation_id_fk")
    private Reservation reservation;
}
