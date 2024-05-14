package com.sonyairlines.tickets.Service;

import com.sonyairlines.tickets.Model.Ticket;
import com.sonyairlines.tickets.Model.VueloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    VueloClient vueloClient;

    @Autowired
    private VueloDto vueloDto;

    private final List<Ticket> tickets = new ArrayList<>();

    //Muestra una lista de Tickets
    public List<Ticket> getAllTickets() {
        return tickets;
    }


    // Mapeo una lista de VueloDto a una lista de Ticket con los detalles del pasajero
    public Ticket ticketMapper(VueloDto vueloDto, Ticket ticket) {
        Ticket newTicket = new Ticket(); //Creo una nueva instancia de ticket
        newTicket.setId(vueloDto.getId());
        newTicket.setVuelo(vueloDto);
        newTicket.setPassengerName(ticket.getPassengerName());
        newTicket.setPassengerEmail(ticket.getPassengerEmail());
        newTicket.setPassengerPassport(ticket.getPassengerPassport());
        return newTicket;
    }

    //Agrego un nuevo ticket a partir de un vuelo en particular a una lista de tickets
    public Optional<Ticket> addTicket(Ticket ticket, VueloDto vueloDto) {
        Ticket mappedTicket = ticketMapper(vueloDto, ticket); // Mapeo el ticket para el vuelo específico
        tickets.add(mappedTicket); // Agrego el ticket mapeado a la lista de tickets del servicio
        return Optional.of(mappedTicket); // Devuelvo el ticket agregado como Optional
    }

    //Elimino un ticket a partir de su ID
    public void deleteTicket(Long ticketId) {
        // Buscar el ticket correspondiente por su ID
        Optional<Ticket> ticketOptional = tickets.stream()
                .filter(ticket -> ticket.getId().equals(ticketId))
                .findFirst();
        // Si el ticket existe, lo elimino de la lista de tickets
        ticketOptional.ifPresent(tickets::remove);
    }

    //Editar un ticket a partir de su ID de la lista de Tickets
    public Optional<Ticket> actualizarTicket(Long ticketId, Ticket ticket) {
        // Recorre la lista de tickets para comparar el ticket con el ID proporcionado
        for (Ticket t : tickets) {
            if (t.getId().equals(ticketId)) {
                // Si encuentra el ticket, establece los nuevos valores
                t.setPassengerName(ticket.getPassengerName());
                t.setPassengerEmail(ticket.getPassengerEmail());
                t.setPassengerPassport(ticket.getPassengerPassport());
                // Devuelve el ticket actualizado
                return Optional.of(t);
            }
        }
        // Si no encuentra el ticket, devuelve Optional vacío
        return Optional.empty();
    }
}
