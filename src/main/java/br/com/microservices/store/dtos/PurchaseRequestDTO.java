package br.com.microservices.store.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseRequestDTO {

    @JsonIgnore
    private Long purchaseId;

    private List<PurchaseItemsRequestDTO> items;
    private AddressRequestDTO address;
}
