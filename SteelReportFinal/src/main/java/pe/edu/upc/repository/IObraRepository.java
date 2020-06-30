package pe.edu.upc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Obra;

@Repository
public interface IObraRepository extends JpaRepository<Obra, Integer>{
	@Query("from Obra j where j.nombreObra like %:nombreObra%")
	List<Obra> buscarNombre(@Param("nombreObra") String nombreObra);
	
	@Query("from Obra j where j.cliente.nombreCliente like %:nombreCliente%")
	List<Obra> buscarCliente(@Param("nombreCliente") String nombreCliente);	
		
}
