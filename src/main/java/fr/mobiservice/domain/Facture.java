package fr.mobiservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factureId;

    @OneToOne
    @JoinColumn(name = "reservation_id_fk")
    private Reservation reservation;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_moyen_paiement")
    private TypeMoyenPaiementEnum typeMoyenPaiementEnum;
}
