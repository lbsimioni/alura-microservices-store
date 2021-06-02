package br.com.alura.microservices.store.clients;

import br.com.alura.microservices.store.dtos.ProviderInfoDTO;
import br.com.alura.microservices.store.dtos.PurchaseItemsRequestDTO;
import br.com.alura.microservices.store.dtos.RequestInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("provider")
public interface ProviderClient {

    @RequestMapping("/info/{state}")
    ProviderInfoDTO getInfoByState(@PathVariable final String state);

    @PostMapping("/requests")
    RequestInfoDTO realizeRequest(final List<PurchaseItemsRequestDTO> items);
}
