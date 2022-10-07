package africa.semicolon.lumExpress.data.repositories;

import africa.semicolon.lumExpress.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
