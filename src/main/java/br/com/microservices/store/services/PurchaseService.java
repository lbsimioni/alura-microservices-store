package br.com.microservices.store.services;

import br.com.microservices.store.client.ProviderClient;
import br.com.microservices.store.controllers.dtos.ProviderInfoDTO;
import br.com.microservices.store.controllers.dtos.PurchaseRequestDTO;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Setter(AccessLevel.PRIVATE)
@Service
@Slf4j
public class PurchaseService {

//    @Autowired
//    private RestTemplate client;

    @Autowired
    private ProviderClient providerClient;

    @Value("${microservices.provider.url}")
    private String providerUrl;

    public void execute(PurchaseRequestDTO requestDTO) {
        // Implementation with RestTemplate
//        var exchange = client.exchange(
//                String.format("%s/info/%s", providerUrl, requestDTO.getAddress().getState()),
//                HttpMethod.GET, null, ProviderInfoDTO.class);

        ProviderInfoDTO info = providerClient.getInfoByState(requestDTO.getAddress().getState());

        log.info("Provider information: " + info);
    }
}
