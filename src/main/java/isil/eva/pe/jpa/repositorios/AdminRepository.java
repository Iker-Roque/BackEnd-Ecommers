package isil.eva.pe.jpa.repositorios;

import isil.eva.pe.jpa.modelo.Admin_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin_, Long> {
    Optional<Admin_> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}