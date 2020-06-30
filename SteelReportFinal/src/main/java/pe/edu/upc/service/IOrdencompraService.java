package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Ordencompra;

public interface IOrdencompraService {	
	public boolean insertar(Ordencompra ordencompra);
	public boolean modificar(Ordencompra ordencompra);
	public void eliminar(int idOrdencompra);
	public Optional<Ordencompra> buscarId(int idOrdencompra);
	public Optional<Ordencompra> listarId(int idOrdencompra);
	List<Ordencompra> listar();
	List<Ordencompra> buscarJefe(String nombJefeventas);
}
