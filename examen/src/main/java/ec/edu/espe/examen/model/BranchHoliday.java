package ec.edu.espe.examen.model;

import lombok.NoArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BranchHoliday {
    private LocalDate date;
    private String name;
} 