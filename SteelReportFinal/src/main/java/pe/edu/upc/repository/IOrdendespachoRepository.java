package pe.edu.upc.repository;

import java.util.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Ordendespacho;

@Repository
public interface IOrdendespachoRepository extends JpaRepository<Ordendespacho, Integer>{
	
	
	List<Ordendespacho> findByFecDateOrdendespacho(Date fecDateOrdendespacho);
	
	@Query("from Ordendespacho od where od.jefeventas.nombJefeventas like %:nombJefeventas%")
	List<Ordendespacho> buscarJefe(@Param("nombJefeventas") String nombJefeventas);	
		
}
