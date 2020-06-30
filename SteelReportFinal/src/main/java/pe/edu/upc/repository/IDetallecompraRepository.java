package pe.edu.upc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Detallecompra;



@Repository
public interface IDetallecompraRepository extends JpaRepository<Detallecompra, Integer>{
	@Query("from Detallecompra d where d.cantiDetallecompra like %:cantiDetallecompra%")
	List<Detallecompra> buscarCantidad(@Param("cantiDetallecompra") int cantiDetallecompra);
	
	@Query("from Detallecompra d where d.ordencompra.idOrdencompra like %:idOrdencompra%")
	List<Detallecompra> buscarOrdencompra(@Param("idOrdencompra") int idOrdencompra);
	
	@Query("from Detallecompra d where d.producto.nomProducto like %:nomProducto%")
	List<Detallecompra> buscarProducto(@Param("nomProducto") String nomProducto);	
	
	
	
}
