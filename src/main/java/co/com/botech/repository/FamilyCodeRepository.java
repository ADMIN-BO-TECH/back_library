package co.com.botech.repository;

import co.com.botech.entity.FamilyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FamilyCodeRepository extends JpaRepository<FamilyCode, Long> {
    Optional<FamilyCode> findByCode(String code);
}
