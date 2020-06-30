package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Solicitudcompra;

public interface ISolicitudcompraService {	
	public boolean insertar(Solicitudcompra solicitudcompra);
	public boolean modificar(Solicitudcompra solicitudcompra);
	public void eliminar(int idSolicitudcompra);
	public Optional<Solicitudcompra> buscarId(int idSolicitudcompra);
	public Optional<Solicitudcompra> listarId(int idSolicitudcompra);
	List<Solicitudcompra> listar();
	List<Solicitudcompra> buscarCliente(String nombreCliente);
}
