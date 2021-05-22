package br.com.microservices.store.store.controllers.dtos.requests;

import lombok.Data;

@Data
public class PurchaseItemsRequestDTO {
    private long id;
    private int amount;
}
