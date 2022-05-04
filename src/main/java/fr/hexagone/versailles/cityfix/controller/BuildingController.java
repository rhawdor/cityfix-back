package fr.hexagone.versailles.cityfix.controller;

import fr.hexagone.versailles.cityfix.domain.Building;
import fr.hexagone.versailles.cityfix.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/buildings")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request")
})
@CrossOrigin(origins = "*")
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    @Operation(description = "Get all buildings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public List<Building> getBuildings() {
        return buildingService.getBuildings();
    }

    @PostMapping
    @Operation(description = "Add a building")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Building addBuilding(@RequestBody @Valid Building building) {
        return buildingService.addBuilding(building);
    }

    @PatchMapping("/{id}")
    @Operation(description = "Update a building")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated")
    })
    public Building updateBuilding(@Parameter(required = true) @PathVariable("id") @NonNull Long id,
                                   @RequestBody @Valid Building building) {
        return buildingService.updateBuilding(id, building);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a building")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted")
    })
    public void deleteBuilding(@Parameter(required = true) @PathVariable("id") @NonNull Long id) {
        buildingService.deleteBuilding(id);
    }

}
