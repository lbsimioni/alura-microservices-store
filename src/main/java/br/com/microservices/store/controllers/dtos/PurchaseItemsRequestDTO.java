package br.com.microservices.store.controllers.dtos;

import lombok.Data;

@Data
public class PurchaseItemsRequestDTO {
    private long id;
    private int amount;
}
