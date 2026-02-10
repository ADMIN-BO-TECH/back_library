package co.com.botech.repository;

import co.com.botech.entity.UserFamily;
import co.com.botech.entity.UserFamilyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFamilyRepository extends JpaRepository<UserFamily, UserFamilyId> {

    boolean existsByUser_IdAndFamily_Id(Long userId, Long familyId);
}