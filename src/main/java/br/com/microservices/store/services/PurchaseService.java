package br.com.microservices.store.services;

import br.com.microservices.store.client.ProviderClient;
import br.com.microservices.store.dtos.ProviderInfoDTO;
import br.com.microservices.store.dtos.PurchaseRequestDTO;
import br.com.microservices.store.dtos.RequestInfoDTO;
import br.com.microservices.store.model.Purchase;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter(AccessLevel.PRIVATE)
@Service
@Slf4j
public class PurchaseService {

    @Autowired
    private ProviderClient providerClient;

    public Purchase execute(PurchaseRequestDTO requestDTO) {

        ProviderInfoDTO info = providerClient.getInfoByState(requestDTO.getAddress().getState());
        log.info("Provider information: " + info);

        RequestInfoDTO request = providerClient.realizeRequest(requestDTO.getItems());
        log.info("Request information: " + request);

        var purchase = new Purchase();
        purchase.setRequestId(request.getId());
        purchase.setPreparationTime(request.getPreparationTime());
        purchase.setDestinyAddress(requestDTO.getAddress().toString());

        return purchase;
    }
}
