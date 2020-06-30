package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Ordendespacho;

public interface IOrdendespachoService {	
	public boolean insertar(Ordendespacho ordendespacho);
	public boolean modificar(Ordendespacho ordendespacho);
	public void eliminar(int idOrdendespacho);
	public Optional<Ordendespacho> buscarId(int idOrdendespacho);
	public Optional<Ordendespacho> listarId(int idOrdendespacho);
	List<Ordendespacho> listar();
	
	List<Ordendespacho> buscarJefe(String nombJefeventas);
}
