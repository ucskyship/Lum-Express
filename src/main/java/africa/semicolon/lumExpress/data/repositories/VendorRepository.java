package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
