package prueba_tecnica.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import prueba_tecnica.Bean.Producto;

public interface ProductoDAO extends JpaRepository<Producto,Long> {

}
