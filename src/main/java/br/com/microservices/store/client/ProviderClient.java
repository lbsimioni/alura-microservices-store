package br.com.microservices.store.client;

import br.com.microservices.store.controllers.dtos.ProviderInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("provider")
public interface ProviderClient {

    @RequestMapping("/info/{state}")
    ProviderInfoDTO getInfoByState(@PathVariable String state);
}
