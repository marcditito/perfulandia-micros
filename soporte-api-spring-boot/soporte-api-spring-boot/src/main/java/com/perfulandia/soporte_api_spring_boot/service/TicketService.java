package com.perfulandia.soporte_api_spring_boot.service;

import com.perfulandia.soporte_api_spring_boot.model.Ticket;
import com.perfulandia.soporte_api_spring_boot.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> obtenerTodosLosTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> obtenerTicketPorId(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket crearTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void eliminarTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
