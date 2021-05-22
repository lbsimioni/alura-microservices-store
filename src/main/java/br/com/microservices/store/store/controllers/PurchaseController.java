package br.com.microservices.store.store.controllers;

import br.com.microservices.store.store.controllers.dtos.PurchaseRequestDTO;
import br.com.microservices.store.store.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@AllArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public void purchase(@RequestBody PurchaseRequestDTO requestDTO){
        purchaseService.execute(requestDTO);
    }
}
