package pe.edu.upc.service;
//js
import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Tipocliente;

public interface ITipoclienteService {
	public boolean insertar(Tipocliente tipocliente);
	public boolean modificar(Tipocliente tipocliente);
	public void eliminar(int idTipocliente);
	public Optional<Tipocliente> buscarId(int idTipocliente);
	public Optional<Tipocliente> listarId(int idTipocliente);
	List<Tipocliente> listar();
	List<Tipocliente> buscarNombre(String nombTipocliente);		
}
