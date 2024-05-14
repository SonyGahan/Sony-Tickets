package com.sonyairlines.tickets.Controller;

import com.sonyairlines.tickets.Model.Ticket;
import com.sonyairlines.tickets.Model.VueloDto;
import com.sonyairlines.tickets.Service.TicketService;
import com.sonyairlines.tickets.Service.VueloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService ticketservice;

    @Autowired
    VueloClient vueloClient;

    @Autowired
    private VueloDto vueloDto;


    //Muestra una lista de Tickets
    @GetMapping("")
    public List<Ticket> getAllTickets() {
        return ticketservice.getAllTickets();
    }

    //Muestra una lista de Vuelos a traves del VueloClient
    @GetMapping("/vuelos")
    public List<VueloDto> getAllFlights(){
        return vueloClient.getAllFlights();
    }

    //Agrega un ticket nuevo a la lista de tickets
    @PostMapping("/addticket")
    public Optional<Ticket> createVuelo(@RequestBody Ticket ticket, @RequestParam VueloDto vueloDto){
        return ticketservice.addTicket(ticket, vueloDto);
    }

    //Busca y elimina un ticket segun su ID de una lista de tickets
    @DeleteMapping("/tickets/eliminar/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketservice.deleteTicket(id);
    }

    //Busca y edita un ticket segun su ID de una lista de tickets
    @PutMapping("/tickets/actualizar/{id}")
    public Optional<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        List<Ticket> tickets = ticketservice.getAllTickets(); // Obtener la lista de tickets
        return ticketservice.actualizarTicket(id, ticket);
    }
}
