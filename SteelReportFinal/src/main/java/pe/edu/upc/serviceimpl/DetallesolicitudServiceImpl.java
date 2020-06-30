package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Detallesolicitud;
import pe.edu.upc.repository.IDetallesolicitudRepository;
import pe.edu.upc.service.IDetallesolicitudService;

@Service
public class DetallesolicitudServiceImpl implements IDetallesolicitudService {

	@Autowired
	private IDetallesolicitudRepository deDetallesolicitud;
	
	@Override
	@Transactional
	public boolean insertar(Detallesolicitud detallesolicitud) {
		Detallesolicitud objDetallesolicitud = deDetallesolicitud.save(detallesolicitud);
		if (objDetallesolicitud == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Detallesolicitud detallesolicitud) {
		boolean flag = false;
		try {
			deDetallesolicitud.save(detallesolicitud);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDetallesolicitud) {
		deDetallesolicitud.deleteById(idDetallesolicitud);		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Detallesolicitud> buscarId(int idDetallesolicitud) {
		return deDetallesolicitud.findById(idDetallesolicitud);
	}	

	@Override
	@Transactional(readOnly=true)
	public Optional<Detallesolicitud> listarId(int idDetallesolicitud) {
		return deDetallesolicitud.findById(idDetallesolicitud);
	}

	@Override
	@Transactional
	public List<Detallesolicitud> listar() {
		return deDetallesolicitud.findAll();
	}

	
	
	@Override
	@Transactional
	public List<Detallesolicitud> buscarProducto(String nomProducto) {
		return deDetallesolicitud.buscarProducto(nomProducto);
	}
	
	@Override
	@Transactional
	public List<Detallesolicitud> buscarSolicitudcompra(int idSolicitudcompra) {
		return deDetallesolicitud.buscarSolicitudcompra(idSolicitudcompra);
	}

	@Override
	@Transactional
	public List<Detallesolicitud> buscarCantidad(int cantiDetallesolicitud) {
		return deDetallesolicitud.buscarCantidad(cantiDetallesolicitud);
	}

	
	
}
