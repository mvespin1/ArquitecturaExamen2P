package ec.edu.espe.examen.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchDTO {

    private String id;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private String state;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private List<BranchHolidayDTO> branchHolidays;
}