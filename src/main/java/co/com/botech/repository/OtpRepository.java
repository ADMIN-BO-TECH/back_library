package co.com.botech.repository;

import co.com.botech.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByEmailAndVerificationIdAndProductKey(
            String email, String verificationId, String productKey);
}
