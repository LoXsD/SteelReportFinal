package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Hojaruta;

public interface IHojarutaService {	
	public boolean insertar(Hojaruta hojaruta);
	public boolean modificar(Hojaruta hojaruta);
	public void eliminar(int idHojaruta);
	public Optional<Hojaruta> buscarId(int idHojaruta);
	public Optional<Hojaruta> listarId(int idHojaruta);
	List<Hojaruta> listar();
	List<Hojaruta> buscarPresu(String presuHojaruta);
	List<Hojaruta> buscarJefe(String nombJefeventas);
}
