package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_requests")
@Getter
@Setter
@NoArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name;
    private String email;
    private String phone;
    private String familyCode;
    private String otpCode;
    private String iv;
    private boolean isValid;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private String verificationId;
    private int attempt;
}