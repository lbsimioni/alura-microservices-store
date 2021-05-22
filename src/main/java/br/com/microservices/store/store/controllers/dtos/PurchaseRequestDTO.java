package br.com.microservices.store.store.controllers.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseRequestDTO {
    private List<PurchaseItemsRequestDTO> items;
    private AddressRequestDTO address;
}
