package com.sonyairlines.tickets.Service;

import com.sonyairlines.tickets.Model.VueloDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "vuelos-api")
public interface VueloClient {

    //Desarrollo los metodos que dan puntos de acceso a "vuelos-api"

    @GetMapping("/vuelos")
    List<VueloDto> getListadoDeVuelos();

    @GetMapping("/vuelos/{id}")
    Optional<VueloDto> getFlightById(@PathVariable Long id);

    @PostMapping("/vuelos/actualizar")
    Optional<VueloDto> updateFlight(@RequestBody VueloDto vuelo);

    @DeleteMapping("/vuelos/eliminar/{id}")
    void deleteFlight(@PathVariable Long id);

}
