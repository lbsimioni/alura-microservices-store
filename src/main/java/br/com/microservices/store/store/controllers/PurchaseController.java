package br.com.microservices.store.store.controllers;

import br.com.microservices.store.store.controllers.dtos.requests.PurchaseRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @PostMapping
    public void purchase(@RequestBody PurchaseRequestDTO requestDTO){

    }
}
