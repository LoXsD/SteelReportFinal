package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer>{
	@Query("from Producto p where p.nomProducto like %:nomProducto%")
	List<Producto> buscarNombre(@Param("nomProducto") String nomProducto);
}
