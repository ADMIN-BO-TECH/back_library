package co.com.botech.repository;

import co.com.botech.entity.RfidRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RfidRegisterRepository extends JpaRepository<RfidRegister, Long> {

    List<RfidRegister> findByKindDevice_IdAndSchool_Id(Long kindDeviceId, Long schoolId);
    Optional<RfidRegister> findByRfidTag(String rfidTag);
    Optional<RfidRegister> findByRfidTagAndKindDevice_Id(String rfidTag, Long kindDeviceId);

}
