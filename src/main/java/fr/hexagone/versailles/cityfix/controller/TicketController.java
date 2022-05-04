package fr.hexagone.versailles.cityfix.controller;

import fr.hexagone.versailles.cityfix.domain.Ticket;
import fr.hexagone.versailles.cityfix.domain.TicketCreation;
import fr.hexagone.versailles.cityfix.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tickets")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request")
})
@CrossOrigin(origins = "*")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    @Operation(description = "Get all tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @PostMapping("/{buildingId}")
    @Operation(description = "Create a ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@RequestBody @Valid TicketCreation ticketCreation,
                               @PathVariable Long buildingId) {
        return ticketService.createTicket(buildingId, ticketCreation);
    }

}
