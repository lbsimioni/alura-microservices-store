package br.com.microservices.store.store.controllers.dtos.requests;

import lombok.Data;

@Data
public class AddressRequestDTO {
    private String street;
    private int number;
    private String state;
}
