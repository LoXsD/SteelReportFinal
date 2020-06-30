package pe.edu.upc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Detallesolicitud;



@Repository
public interface IDetallesolicitudRepository extends JpaRepository<Detallesolicitud, Integer>{
	@Query("from Detallesolicitud de where de.cantiDetallesolicitud like %:cantiDetallesolicitud%")
	List<Detallesolicitud> buscarCantidad(@Param("cantiDetallesolicitud") int cantiDetallesolicitud);
	
	@Query("from Detallesolicitud de where de.solicitudcompra.idSolicitudcompra like %:idSolicitudcompra%")
	List<Detallesolicitud> buscarSolicitudcompra(@Param("idSolicitudcompra") int idSolicitudcompra);
	
	@Query("from Detallesolicitud de where de.producto.nomProducto like %:nomProducto%")
	List<Detallesolicitud> buscarProducto(@Param("nomProducto") String nomProducto);	
	
	
	
}
