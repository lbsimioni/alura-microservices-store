package br.com.microservices.store.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Purchase {
    @Id
    private Long requestId;
    private Integer preparationTime;
    private String destinyAddress;
}
