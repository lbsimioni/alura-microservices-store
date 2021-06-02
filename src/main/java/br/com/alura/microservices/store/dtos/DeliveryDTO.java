package br.com.alura.microservices.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDTO {
    private Long requestId;
    private LocalDate searchDate;
    private LocalDate forecastDate;
    private String originAddress;
    private String destinationAddress;
}
