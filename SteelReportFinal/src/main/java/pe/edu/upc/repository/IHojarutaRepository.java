package pe.edu.upc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Hojaruta;

@Repository
public interface IHojarutaRepository extends JpaRepository<Hojaruta, Integer>{
	@Query("from Hojaruta h where h.presuHojaruta like %:presuHojaruta%")
	List<Hojaruta> buscarPresu(@Param("presuHojaruta") String presuHojaruta);
	
	@Query("from Hojaruta h where h.jefeventas.nombJefeventas like %:nombJefeventas%")
	List<Hojaruta> buscarJefe(@Param("nombJefeventas") String nombJefeventas);	
		
}
