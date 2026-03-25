package co.com.botech.repository;

import co.com.botech.entity.Family;
import co.com.botech.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByFamilyCode(String familyCode);
    Optional<Family> findBySchool_IdAndFamilyCode(Long schoolId, String familyCode);
}
