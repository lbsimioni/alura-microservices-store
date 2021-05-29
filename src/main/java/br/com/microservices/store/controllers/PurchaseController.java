package br.com.microservices.store.controllers;

import br.com.microservices.store.dtos.PurchaseRequestDTO;
import br.com.microservices.store.model.Purchase;
import br.com.microservices.store.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@AllArgsConstructor
public class PurchaseController {

    @Autowired
    private final PurchaseService purchaseService;

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Purchase> purchase(@RequestBody PurchaseRequestDTO requestDTO){
        return ResponseEntity.ok(purchaseService.execute(requestDTO));
    }
}
