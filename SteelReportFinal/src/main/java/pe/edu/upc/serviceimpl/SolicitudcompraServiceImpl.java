package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Solicitudcompra;
import pe.edu.upc.repository.ISolicitudcompraRepository;
import pe.edu.upc.service.ISolicitudcompraService;


@Service
public class SolicitudcompraServiceImpl implements ISolicitudcompraService {

	
	@Autowired
	private ISolicitudcompraRepository dSolicitudcompra;
	
	
	
	@Override
	@Transactional
	public boolean insertar(Solicitudcompra solicitudcompra) {
		Solicitudcompra objSolicitudcompra = dSolicitudcompra.save(solicitudcompra);
		if (objSolicitudcompra == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Solicitudcompra solicitudcompra) {
		boolean flag = false;
		try {
			dSolicitudcompra.save(solicitudcompra);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idSolicitudcompra) {
		dSolicitudcompra.deleteById(idSolicitudcompra);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Solicitudcompra> buscarId(int idSolicitudcompra) {
		return dSolicitudcompra.findById(idSolicitudcompra);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Solicitudcompra> listarId(int idSolicitudcompra) {
		return dSolicitudcompra.findById(idSolicitudcompra);
	}

	@Override
	@Transactional
	public List<Solicitudcompra> listar() {
		return dSolicitudcompra.findAll();
	}

	/*@Override
	@Transactional
	public List<Ordencompra> buscarNombre(String nombJefeventa) {
		return dOrdencompra.buscarNombre(nombJefeventas);
	}*/

	@Override
	@Transactional
	public List<Solicitudcompra> buscarCliente(String nombreCliente) {
		return dSolicitudcompra.buscarCliente(nombreCliente);
	}

	
	
}
