package ec.edu.espe.examen.service;

import org.springframework.stereotype.Service;
import ec.edu.espe.examen.exception.NotFoundException;
import ec.edu.espe.examen.model.Branch;
import ec.edu.espe.examen.model.BranchHoliday;
import ec.edu.espe.examen.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    
    private final BranchRepository branchRepository;

    public List<Branch> findAll() {
        return this.branchRepository.findAll();
    }

    public Branch findById(String id) {
        return this.branchRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Branch", id));    
    }

    public Branch create(Branch branch) {
        branch.setCreationDate(LocalDateTime.now());
        branch.setLastModifiedDate(LocalDateTime.now());
        return this.branchRepository.save(branch);
    }

    public Branch updatePhoneNumber(String id, String phoneNumber) {
        Branch branch = this.findById(id);
        branch.setPhoneNumber(phoneNumber);
        branch.setLastModifiedDate(LocalDateTime.now());
        return this.branchRepository.save(branch);
    }

    public Branch addHoliday(String id, BranchHoliday holiday) {
        Branch branch = this.findById(id);
        branch.getBranchHolidays().add(holiday);
        branch.setLastModifiedDate(LocalDateTime.now());
        return this.branchRepository.save(branch);
    }

    public Branch removeHoliday(String id, LocalDate date) {
        Branch branch = this.findById(id);
        branch.getBranchHolidays().removeIf(holiday -> holiday.getDate().equals(date));
        branch.setLastModifiedDate(LocalDateTime.now());
        return this.branchRepository.save(branch);
    }

    public List<BranchHoliday> getHolidays(String id) {
        Branch branch = this.findById(id);
        return branch.getBranchHolidays();
    }

    public boolean isHoliday(String id, LocalDate date) {
        Branch branch = this.findById(id);
        return branch.getBranchHolidays().stream()
            .anyMatch(holiday -> holiday.getDate().equals(date));
    }
} 