package pe.edu.upc.repository;
//js
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Tipocliente;



@Repository
public interface ITipoclienteRepository extends JpaRepository<Tipocliente, Integer>{
	@Query("from Tipocliente t where t.nombTipocliente like %:nombTipocliente%")
	List<Tipocliente> buscarNombre(@Param("nombTipocliente") String nombTipocliente);
}


