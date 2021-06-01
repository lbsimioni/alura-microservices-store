package br.com.microservices.store.services;

import br.com.microservices.store.clients.DeliveryClient;
import br.com.microservices.store.clients.ProviderClient;
import br.com.microservices.store.dtos.DeliveryDTO;
import br.com.microservices.store.dtos.ProviderInfoDTO;
import br.com.microservices.store.dtos.PurchaseRequestDTO;
import br.com.microservices.store.dtos.RequestInfoDTO;
import br.com.microservices.store.exceptions.ResourceNotFoundException;
import br.com.microservices.store.models.Purchase;
import br.com.microservices.store.models.enums.PurchaseState;
import br.com.microservices.store.repositories.PurchaseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@AllArgsConstructor
public class PurchaseService {

    @Autowired
    private final ProviderClient providerClient;

    @Autowired
    private final DeliveryClient deliveryClient;

    @Autowired
    private final PurchaseRepository purchaseRepository;

    // Bulkhead
    @HystrixCommand(threadPoolKey = "GetPurchaseByIdThreadPool")
    public Purchase getById(final Long id) {
        return purchaseRepository.findById(id).orElseThrow(() -> {
            log.warn("Purchase not found with id: {}", id);
            throw new ResourceNotFoundException("Purchase not found");
        });
    }

    @HystrixCommand(fallbackMethod = "creationPurchaseFallback",
            threadPoolKey = "creationPurchaseThreadPool")
    public Purchase execute(final PurchaseRequestDTO requestDTO) {

        var purchase = purchaseRepository.save(Purchase.builder()
                .state(PurchaseState.RECEIVED)
                .destinyAddress(requestDTO.getAddress().toString())
                .build());

        requestDTO.setPurchaseId(purchase.getId());

        ProviderInfoDTO info = providerClient.getInfoByState(requestDTO.getAddress().getState());
        log.info("Provider information: " + info);

        RequestInfoDTO request = providerClient.realizeRequest(requestDTO.getItems());
        log.info("Request information: " + request);
        purchase = purchaseRepository.save(purchase.toBuilder()
                .state(PurchaseState.REQUEST_REALIZED)
                .requestId(request.getId())
                .preparationTime(request.getPreparationTime())
                .build());

        var deliveryDTO = DeliveryDTO.builder()
                .requestId(request.getId())
                .forecastDate(LocalDate.now().plusDays(request.getPreparationTime()))
                .originAddress(info.getAddress())
                .destinationAddress(requestDTO.getAddress().toString())
                .build();

        var voucher = deliveryClient.reservationDelivery(deliveryDTO);

        return purchaseRepository.save(purchase.toBuilder()
                .state(PurchaseState.DELIVERY_RESERVED)
                .deliveryForecast(voucher.getDeliveryForecast())
                .voucher(voucher.getNumber())
                .build());
    }

    public Purchase creationPurchaseFallback(final PurchaseRequestDTO requestDTO) {
        if(requestDTO.getPurchaseId() != null) return getById(requestDTO.getPurchaseId());

        log.warn("Doing creation purchase fallback for request: {}", requestDTO);
        var purchase = new Purchase();
        purchase.setDestinyAddress(requestDTO.getAddress().toString());
        return purchase;
    }
}
