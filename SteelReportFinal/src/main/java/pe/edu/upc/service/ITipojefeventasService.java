package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Tipojefeventas;

public interface ITipojefeventasService {
	public boolean insertar(Tipojefeventas tipojefeventas);
	public boolean modificar(Tipojefeventas tipojefeventas);
	public void eliminar(int idTipojefeventas);
	public Optional<Tipojefeventas> buscarId(int idTipojefeventas);
	public Optional<Tipojefeventas> listarId(int idTipojefeventas);
	List<Tipojefeventas> listar();
	List<Tipojefeventas> buscarNombre(String nombreTipo);		
}
