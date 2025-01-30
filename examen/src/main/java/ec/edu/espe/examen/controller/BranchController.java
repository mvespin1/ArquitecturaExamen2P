package ec.edu.espe.examen.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.examen.controller.dto.BranchDTO;
import ec.edu.espe.examen.controller.dto.BranchHolidayDTO;
import ec.edu.espe.examen.controller.mapper.BranchMapper;
import ec.edu.espe.examen.model.Branch;
import ec.edu.espe.examen.model.BranchHoliday;
import ec.edu.espe.examen.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/branches")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Branch", description = "API para la gestión de sucursales")
public class BranchController {

    private final BranchService branchService;
    private final BranchMapper mapper;

    @GetMapping
    @Operation(summary = "Obtener todas las sucursales")
    @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida de manera exitosa")
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        log.info("Obteniendo todas las sucursales");

        List<Branch> branches = this.branchService.findAll();
        List<BranchDTO> dtos = new ArrayList<>();

        for (Branch branch : branches) {
            dtos.add(this.mapper.toDTO(branch));
        }

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sucursal")
    @ApiResponse(responseCode = "200", description = "Sucursal creada de manera exitosa")
    public ResponseEntity<BranchDTO> createBranch(
            @Valid @RequestBody BranchDTO branchDTO) {
        log.info("Creando nueva sucursal: {}", branchDTO);
        Branch branch = this.branchService.create(this.mapper.toModel(branchDTO));

        return ResponseEntity.ok(this.mapper.toDTO(branch));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una sucursal por su ID")
    @ApiResponse(responseCode = "200", description = "Sucursal encontrada de manera exitosa")
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    public ResponseEntity<BranchDTO> getBranchById(
            @Parameter(description = "ID de la sucursal") @PathVariable String id) {
        log.info("Obteniendo sucursal con id: {}", id);
        Branch branch = this.branchService.findById(id);

        return ResponseEntity.ok(this.mapper.toDTO(branch));
    }

    @PatchMapping("/{id}/phone")
    public ResponseEntity<BranchDTO> updatePhoneNumber(@PathVariable String id, @RequestParam String phoneNumber) {
        Branch branch = this.branchService.updatePhoneNumber(id, phoneNumber);

        return ResponseEntity.ok(this.mapper.toDTO(branch));
    }

    @PostMapping("/{id}/holidays")
    public ResponseEntity<BranchDTO> addHoliday(@PathVariable String id, @RequestBody BranchHolidayDTO holidayDTO) {
        BranchHoliday holiday = new BranchHoliday();
        holiday.setDate(holidayDTO.getDate());
        holiday.setName(holidayDTO.getName());
        Branch branch = this.branchService.addHoliday(id, holiday);
        return ResponseEntity.ok(this.mapper.toDTO(branch));
    }

    @DeleteMapping("/{id}/holidays")
    public ResponseEntity<BranchDTO> removeHoliday(@PathVariable String id, @RequestParam LocalDate date) {
        Branch branch = this.branchService.removeHoliday(id, date);

        return ResponseEntity.ok(this.mapper.toDTO(branch));
    }

    @GetMapping("/{id}/holidays")
    public ResponseEntity<List<BranchHolidayDTO>> getHolidays(@PathVariable String id) {
        List<BranchHoliday> holidays = this.branchService.getHolidays(id);
        List<BranchHolidayDTO> dtos = new ArrayList<>();

        for (BranchHoliday holiday : holidays) {
            BranchHolidayDTO dto = new BranchHolidayDTO();
            dto.setDate(holiday.getDate());
            dto.setName(holiday.getName());
            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);
    }

}
