package fr.mobiservice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.mobiservice.domain.Facture;
import fr.mobiservice.service.FactureService;

@RestController
@CrossOrigin("http://localhost:4200")
public class FactureController {

    private FactureService factureService;

    public FactureController(final FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping(value = "/factures", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Facture>> findAll() {
        return ResponseEntity.ok(factureService.getSortedFactures());
    }
}
