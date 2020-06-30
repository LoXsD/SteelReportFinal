package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Detallecompra;

public interface IDetallecompraService {	
	public boolean insertar(Detallecompra detallecompra);
	public boolean modificar(Detallecompra detallecompra);
	public void eliminar(int idDetallecompra);
	public Optional<Detallecompra> buscarId(int idDetallecompra);
	public Optional<Detallecompra> listarId(int idDetallecompra);
	List<Detallecompra> listar();
	List<Detallecompra> buscarCantidad(int cantiDetallecompra);
	List<Detallecompra> buscarProducto(String nomProducto);
	List<Detallecompra> buscarOrdencompra(int idOrdencompra);
}
