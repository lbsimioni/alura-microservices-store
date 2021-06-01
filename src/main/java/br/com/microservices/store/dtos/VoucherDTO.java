package br.com.microservices.store.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VoucherDTO {
    private Long number;
    private LocalDate deliveryForecast;
}
