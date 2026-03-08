package co.com.botech.repository;

import co.com.botech.entity.SchoolGeofences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolGeofencesRepository extends JpaRepository<SchoolGeofences, Long> {
    Optional<SchoolGeofences> findBySchool_Id(Long schoolId);
}
