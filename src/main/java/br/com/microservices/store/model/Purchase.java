package br.com.microservices.store.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Purchase {
    @Id
    private Long requestId;
    private Integer preparationTime;
    private String destinyAddress;
    private LocalDate deliveryForecast;
    private Long voucher;
}
