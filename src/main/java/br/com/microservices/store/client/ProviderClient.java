package br.com.microservices.store.client;

import br.com.microservices.store.dtos.ProviderInfoDTO;
import br.com.microservices.store.dtos.PurchaseItemsRequestDTO;
import br.com.microservices.store.dtos.RequestInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("provider")
public interface ProviderClient {

    @RequestMapping("/info/{state}")
    ProviderInfoDTO getInfoByState(@PathVariable String state);

    @PostMapping("/requests")
    RequestInfoDTO realizeRequest(List<PurchaseItemsRequestDTO> items);
}
