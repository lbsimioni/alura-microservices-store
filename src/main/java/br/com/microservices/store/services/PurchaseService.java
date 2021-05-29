package br.com.microservices.store.services;

import br.com.microservices.store.client.ProviderClient;
import br.com.microservices.store.dtos.ProviderInfoDTO;
import br.com.microservices.store.dtos.PurchaseRequestDTO;
import br.com.microservices.store.dtos.RequestInfoDTO;
import br.com.microservices.store.exceptions.ResourceNotFoundException;
import br.com.microservices.store.model.Purchase;
import br.com.microservices.store.repositories.PurchaseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PurchaseService {

    @Autowired
    private final ProviderClient providerClient;

    @Autowired
    private final PurchaseRepository purchaseRepository;

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

        ProviderInfoDTO info = providerClient.getInfoByState(requestDTO.getAddress().getState());
        log.info("Provider information: " + info);

        RequestInfoDTO request = providerClient.realizeRequest(requestDTO.getItems());
        log.info("Request information: " + request);

        var purchase = new Purchase();
        purchase.setRequestId(request.getId());
        purchase.setPreparationTime(request.getPreparationTime());
        purchase.setDestinyAddress(requestDTO.getAddress().toString());

        purchaseRepository.save(purchase);

        return purchase;
    }

    public Purchase creationPurchaseFallback(final PurchaseRequestDTO requestDTO) {
        log.warn("Doing creation purchase fallback for request: {}", requestDTO);
        var purchase = new Purchase();
        purchase.setDestinyAddress(requestDTO.getAddress().toString());
        return purchase;
    }
}
