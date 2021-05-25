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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Setter(AccessLevel.PRIVATE)
@Service
@Slf4j
public class PurchaseService {

//    @Autowired
//    private RestTemplate client;

//    @Value("${microservices.provider.url}")
//    private String providerUrl;

    @Autowired
    private ProviderClient providerClient;

    public Purchase execute(PurchaseRequestDTO requestDTO) {
        // Implementation with RestTemplate
//        var exchange = client.exchange(
//                String.format("%s/info/%s", providerUrl, requestDTO.getAddress().getState()),
//                HttpMethod.GET, null, ProviderInfoDTO.class);

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
