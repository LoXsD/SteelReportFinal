package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Detallesolicitud;

public interface IDetallesolicitudService  {	
	public boolean insertar(Detallesolicitud detallesolicitud);
	public boolean modificar(Detallesolicitud detallesolicitud);
	public void eliminar(int idDetallesolicitud);
	public Optional<Detallesolicitud> buscarId(int idDetallesolicitud);
	public Optional<Detallesolicitud> listarId(int idDetallesolicitud);
	List<Detallesolicitud> listar();
	List<Detallesolicitud> buscarCantidad(int cantiDetallesolicitud);
	List<Detallesolicitud> buscarProducto(String nomProducto);
	List<Detallesolicitud> buscarSolicitudcompra(int idSolicitudcompra);
}
