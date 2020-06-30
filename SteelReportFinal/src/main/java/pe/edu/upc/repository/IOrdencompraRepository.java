package pe.edu.upc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Ordencompra;

@Repository
public interface IOrdencompraRepository extends JpaRepository<Ordencompra, Integer>{

	
	@Query("from Ordencompra o where o.jefeventas.nombJefeventas like %:nombJefeventas%")
	List<Ordencompra> buscarJefe(@Param("nombJefeventas") String nombJefeventas);	
	
	
	
}
