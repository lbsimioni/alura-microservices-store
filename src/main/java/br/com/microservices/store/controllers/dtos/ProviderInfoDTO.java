package br.com.microservices.store.controllers.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProviderInfoDTO {
    private Long id;
    private String name;
    private String state;
    private String address;
}
