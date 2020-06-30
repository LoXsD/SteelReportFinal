package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Jefeventas;

public interface IJefeventasService {	
	public boolean insertar(Jefeventas jefeventas);
	public boolean modificar(Jefeventas jefeventas);
	public void eliminar(int idJefeventas);
	public Optional<Jefeventas> buscarId(int idJefeventas);
	public Optional<Jefeventas> listarId(int idJefeventas);
	List<Jefeventas> listar();
	List<Jefeventas> buscarNombre(String nombJefeventas);
	List<Jefeventas> buscarTipo(String nombreTipo);
}
