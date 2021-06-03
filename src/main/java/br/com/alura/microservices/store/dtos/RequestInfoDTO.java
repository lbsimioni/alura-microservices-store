package br.com.alura.microservices.store.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RequestInfoDTO {
    private Long id;
    private Integer preparationTime;
}
