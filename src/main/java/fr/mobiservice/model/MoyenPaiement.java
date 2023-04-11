package fr.mobiservice.model;

import fr.mobiservice.domain.TypeMoyenPaiementEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoyenPaiement {

    private String cardNumber;
    private String expirationDate;
    private String email;

    public boolean isCardPaiement() {
        return !this.getCardNumber().isEmpty() && !this.getExpirationDate().isEmpty();
    }

    public boolean isPaypalPaiement() {
        return !this.getEmail().isEmpty();
    }

    public TypeMoyenPaiementEnum getTypeMoyenPaiement() {
        if (this.isCardPaiement()) {
            return TypeMoyenPaiementEnum.CARTE_BANCAIRE;
        }
        if (this.isPaypalPaiement()) {
            return TypeMoyenPaiementEnum.PAYPAL;
        }
        return TypeMoyenPaiementEnum.NON_RECONNU;
    }
}
