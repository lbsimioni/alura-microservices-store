package br.com.microservices.store.services;

import br.com.microservices.store.controllers.dtos.PurchaseRequestDTO;
import br.com.microservices.store.controllers.dtos.ProviderInfoDTO;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Setter(AccessLevel.PRIVATE)
@Service
public class PurchaseService {

    @Value("${microservices.provider.url}")
    private String providerUrl;

    public void execute(PurchaseRequestDTO requestDTO) {
        var client = new RestTemplate();
        var exchange = client.exchange(
                String.format("%s/information/%s", providerUrl, requestDTO.getAddress().getState()),
                HttpMethod.GET, null, ProviderInfoDTO.class);


    }
}
