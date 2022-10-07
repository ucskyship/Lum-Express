package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
