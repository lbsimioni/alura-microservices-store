package br.com.microservices.store.controllers;

import br.com.microservices.store.controllers.dtos.PurchaseRequestDTO;
import br.com.microservices.store.services.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void purchase(@RequestBody PurchaseRequestDTO requestDTO){
        purchaseService.execute(requestDTO);
    }
}
