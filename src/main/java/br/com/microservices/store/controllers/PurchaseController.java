package br.com.microservices.store.controllers;

import br.com.microservices.store.dtos.PurchaseRequestDTO;
import br.com.microservices.store.models.Purchase;
import br.com.microservices.store.services.PurchaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/purchases")
@AllArgsConstructor
@Slf4j
public class PurchaseController {

    @Autowired
    private final PurchaseService purchaseService;

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getById(@PathVariable final Long id) {
        log.info("Getting purchase with id: {}", id);
        var purchase = purchaseService.getById(id);
        log.info("Gatted purchase: {}", purchase);
        return ResponseEntity.ok(purchase);
    }

    @PostMapping
    public ResponseEntity<Purchase> purchase(
            @RequestBody final PurchaseRequestDTO requestDTO,
            UriComponentsBuilder uriBuilder) {
        log.info("Creating purchase for: {}", requestDTO);
        var purchase = purchaseService.execute(requestDTO);
        log.info("Purchase created: {}", purchase);

        var uri = uriBuilder.path("/purchases/{id}").buildAndExpand(purchase.getRequestId()).toUri();

        return ResponseEntity.created(uri).body(purchase);
    }
}
