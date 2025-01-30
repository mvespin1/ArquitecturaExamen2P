package ec.edu.espe.examen.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ec.edu.espe.examen.model.Branch;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {
} 