package co.com.botech.repository;

import co.com.botech.entity.AuthorizedPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizedPersonRepository extends JpaRepository<AuthorizedPerson, Long> {}