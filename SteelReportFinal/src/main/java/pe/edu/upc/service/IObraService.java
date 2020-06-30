package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Obra;

public interface IObraService {	
	public boolean insertar(Obra obra);
	public boolean modificar(Obra obra);
	public void eliminar(int idObra);
	public Optional<Obra> buscarId(int idObra);
	public Optional<Obra> listarId(int idObra);
	List<Obra> listar();
	List<Obra> buscarNombre(String nombreObra);
	List<Obra> buscarCliente(String nombreCliente);
}
