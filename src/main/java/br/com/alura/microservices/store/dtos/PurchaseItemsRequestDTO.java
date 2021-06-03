package br.com.alura.microservices.store.dtos;

import lombok.Data;

@Data
public class PurchaseItemsRequestDTO {
    private long id;
    private int amount;
}
