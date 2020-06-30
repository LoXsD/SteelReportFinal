package pe.edu.upc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Jefeventas;

@Repository
public interface IJefeventasRepository extends JpaRepository<Jefeventas, Integer>{
	@Query("from Jefeventas j where j.nombJefeventas like %:nombJefeventas%")
	List<Jefeventas> buscarNombre(@Param("nombJefeventas") String nombJefeventas);
	
	@Query("from Jefeventas j where j.tipojefeventas.nombreTipo like %:nombreTipo%")
	List<Jefeventas> buscarTipo(@Param("nombreTipo") String nombreTipo);	
		
}
