package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Admin;
import africa.semicolon.lumExpress.data.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByEmail(String email);
}
