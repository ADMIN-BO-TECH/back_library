package co.com.botech.repository;

import co.com.botech.entity.KindDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KindDeviceRepository extends JpaRepository<KindDevice, Long> {
    Optional<KindDevice> findByDescription(String description);
}
