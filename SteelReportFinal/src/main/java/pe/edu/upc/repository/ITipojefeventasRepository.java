package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Tipojefeventas;



@Repository
public interface ITipojefeventasRepository extends JpaRepository<Tipojefeventas, Integer>{
	@Query("from Tipojefeventas t where t.nombreTipo like %:nombreTipo%")
	List<Tipojefeventas> buscarNombre(@Param("nombreTipo") String nombreTipo);
}


