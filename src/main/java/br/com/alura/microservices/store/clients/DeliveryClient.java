package br.com.alura.microservices.store.clients;

import br.com.alura.microservices.store.dtos.DeliveryDTO;
import br.com.alura.microservices.store.dtos.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("delivery")
public interface DeliveryClient {
    @PostMapping("/deliveries")
    VoucherDTO reservationDelivery(final DeliveryDTO deliveryDTO);
}
