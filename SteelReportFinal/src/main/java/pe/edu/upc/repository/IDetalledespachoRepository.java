package pe.edu.upc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Detalledespacho;

@Repository
public interface IDetalledespachoRepository extends JpaRepository<Detalledespacho, Integer>{
	@Query("from Detalledespacho l where l.nombdDespacho like %:nombdDespacho%")
	List<Detalledespacho> buscarNombre(@Param("nombdDespacho") String nombdDespacho);
	
	@Query("from Detalledespacho l where l.ordendespacho.idOrdendespacho like %:idOrdendespacho%")
	List<Detalledespacho> buscarOrdendespacho(@Param("idOrdendespacho") int idOrdendespacho);	
	
	@Query("from Detalledespacho l where l.producto.nomProducto like %:nomProducto%")
	List<Detalledespacho> buscarProducto(@Param("nomProducto") String nomProducto);	
	
}
