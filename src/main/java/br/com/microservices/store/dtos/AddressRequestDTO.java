package br.com.microservices.store.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressRequestDTO {
    private String street;
    private int number;
    private String state;
}
