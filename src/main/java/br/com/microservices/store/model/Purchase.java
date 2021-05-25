package br.com.microservices.store.model;

import lombok.Data;

@Data
public class Purchase {
    private Long requestId;
    private Integer preparationTime;
    private String destinyAddress;
}
