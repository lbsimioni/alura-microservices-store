package br.com.microservices.store.controllers.dtos;

import lombok.Data;

@Data
public class AddressRequestDTO {
    private String street;
    private int number;
    private String state;
}
