package ec.edu.espe.examen.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ec.edu.espe.examen.controller.dto.BranchDTO;
import ec.edu.espe.examen.model.Branch;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BranchMapper {

    BranchDTO toDTO(Branch model);

    Branch toModel(BranchDTO dto);
    
} 