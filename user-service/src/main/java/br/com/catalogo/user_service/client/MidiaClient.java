package br.com.catalogo.user_service.client;

import br.com.catalogo.user_service.usuario.dtos.feign.MidiaFeignResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "media-catalog-service")
public interface MidiaClient {

    @GetMapping("Endpoint")
    MidiaFeignResponseDTO buscarMidiaPorId(@PathVariable Long id);

}
