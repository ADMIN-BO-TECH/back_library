package co.com.botech.repository;

import co.com.botech.entity.Family;
import co.com.botech.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findByFamilyCode(String familyCode);
    Optional<Family> findBySchool_IdAndFamilyCode(Long schoolId, String familyCode);
    @Query("""
    select f from Family f
    where f.school.id = :schoolId
      and not exists (select 1 from UserFamily uf where uf.family = f)
""")
    List<Family> findFamiliesWithoutUsers(@Param("schoolId") Long schoolId);

}
