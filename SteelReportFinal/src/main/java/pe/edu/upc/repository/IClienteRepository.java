package pe.edu.upc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	@Query("from Cliente j where j.nombreCliente like %:nombreCliente%")
	List<Cliente> buscarNombre(@Param("nombreCliente") String nombreCliente);
	
	@Query("from Cliente j where j.tipocliente.nombTipocliente like %:nombTipocliente%")
	List<Cliente> buscarTipo(@Param("nombTipocliente") String nombTipocliente);	
	
	@Query("from Cliente j where j.jefeventas.nombJefeventas like %:nombJefeventas%")
	List<Cliente> buscarJefe(@Param("nombJefeventas") String nombJefeventas);	
	
	
}
