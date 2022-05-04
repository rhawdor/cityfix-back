package fr.hexagone.versailles.cityfix.controller;

import fr.hexagone.versailles.cityfix.domain.DashboardContext;
import fr.hexagone.versailles.cityfix.service.ContextService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/context")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request")
})
@CrossOrigin(origins = "*")
public class ContextController {

    private final ContextService contextService;

    public ContextController(ContextService contextService) {
        this.contextService = contextService;
    }

    @GetMapping
    @Operation(description = "Get dashbord context")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    DashboardContext getDashboardContext() {
        return contextService.getDashboardContext();
    }

}
