package co.com.botech.repository;

import co.com.botech.entity.StopInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopInformationRepository extends JpaRepository<StopInformation, Long> {}