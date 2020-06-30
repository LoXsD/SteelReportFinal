package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Detalledespacho;

public interface IDetalledespachoService {	
	public boolean insertar(Detalledespacho detalledespacho);
	public boolean modificar(Detalledespacho detalledespacho);
	public void eliminar(int idDetalledespacho);
	public Optional<Detalledespacho> buscarId(int idDetalledespacho);
	public Optional<Detalledespacho> listarId(int idDetalledespacho);
	List<Detalledespacho> listar();
	List<Detalledespacho> buscarNombre(String nombdDespacho);
	List<Detalledespacho> buscarOrdendespacho(int idOrdendespacho);
	List<Detalledespacho> buscarProducto(String nomProducto);
}
