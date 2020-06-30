package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Detallecompra;
import pe.edu.upc.repository.IDetallecompraRepository;
import pe.edu.upc.service.IDetallecompraService;

@Service
public class DetallecompraServiceImpl implements IDetallecompraService {

	@Autowired
	private IDetallecompraRepository dDetallecompra;
	
	@Override
	@Transactional
	public boolean insertar(Detallecompra detallecompra) {
		Detallecompra objDetallecompra = dDetallecompra.save(detallecompra);
		if (objDetallecompra == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Detallecompra detallecompra) {
		boolean flag = false;
		try {
			dDetallecompra.save(detallecompra);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDetallecompra) {
		dDetallecompra.deleteById(idDetallecompra);		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Detallecompra> buscarId(int idDetallecompra) {
		return dDetallecompra.findById(idDetallecompra);
	}	

	@Override
	@Transactional(readOnly=true)
	public Optional<Detallecompra> listarId(int idDetallecompra) {
		return dDetallecompra.findById(idDetallecompra);
	}

	@Override
	@Transactional
	public List<Detallecompra> listar() {
		return dDetallecompra.findAll();
	}

	
	@Override
	@Transactional
	public List<Detallecompra> buscarProducto(String nomProducto) {
		return dDetallecompra.buscarProducto(nomProducto);
	}
	
	@Override
	@Transactional
	public List<Detallecompra> buscarOrdencompra(int idOrdencompra) {
		return dDetallecompra.buscarOrdencompra(idOrdencompra);
	}

	@Override
	@Transactional
	public List<Detallecompra> buscarCantidad(int cantiDetallecompra) {
		return dDetallecompra.buscarOrdencompra(cantiDetallecompra);
	}

	
	
}
