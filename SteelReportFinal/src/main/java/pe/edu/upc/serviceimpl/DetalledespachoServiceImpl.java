package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Detalledespacho;
import pe.edu.upc.repository.IDetalledespachoRepository;
import pe.edu.upc.service.IDetalledespachoService;


@Service
public class DetalledespachoServiceImpl implements IDetalledespachoService {

	
	@Autowired
	private IDetalledespachoRepository dDetalledespacho;
	
	
	
	@Override
	@Transactional
	public boolean insertar(Detalledespacho detalledespacho) {
		Detalledespacho objDetalledespacho = dDetalledespacho.save(detalledespacho);
		if (objDetalledespacho == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Detalledespacho detalledespacho) {
		boolean flag = false;
		try {
			dDetalledespacho.save(detalledespacho);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idDetalledespacho) {
		dDetalledespacho.deleteById(idDetalledespacho);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Detalledespacho> buscarId(int idDetalledespacho) {
		return dDetalledespacho.findById(idDetalledespacho);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Detalledespacho> listarId(int idDetalledespacho) {
		return dDetalledespacho.findById(idDetalledespacho);
	}

	@Override
	@Transactional
	public List<Detalledespacho> listar() {
		return dDetalledespacho.findAll();
	}
	
	@Override
	@Transactional
	public List<Detalledespacho> buscarNombre(String nombdDespacho) {
		return dDetalledespacho.buscarNombre(nombdDespacho);
	}

	@Override
	@Transactional
	public List<Detalledespacho> buscarOrdendespacho(int idOrdendespacho) {
		return dDetalledespacho.buscarOrdendespacho(idOrdendespacho);
	}

	@Override
	@Transactional
	public List<Detalledespacho> buscarProducto(String nomProducto) {
		return dDetalledespacho.buscarProducto(nomProducto);
	}

	
	
}
