package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Ordendespacho;
import pe.edu.upc.repository.IOrdendespachoRepository;
import pe.edu.upc.service.IOrdendespachoService;


@Service
public class OrdendespachoServiceImpl implements IOrdendespachoService {

	
	@Autowired
	private IOrdendespachoRepository dOrdendespacho;
	
	
	
	@Override
	@Transactional
	public boolean insertar(Ordendespacho ordendespacho) {
		Ordendespacho objOrdendespacho = dOrdendespacho.save(ordendespacho);
		if (objOrdendespacho == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Ordendespacho ordendespacho) {
		boolean flag = false;
		try {
			dOrdendespacho.save(ordendespacho);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idOrdendespacho) {
		dOrdendespacho.deleteById(idOrdendespacho);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Ordendespacho> buscarId(int idOrdendespacho) {
		return dOrdendespacho.findById(idOrdendespacho);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Ordendespacho> listarId(int idOrdendespacho) {
		return dOrdendespacho.findById(idOrdendespacho);
	}

	@Override
	@Transactional
	public List<Ordendespacho> listar() {
		return dOrdendespacho.findAll();
	}

	/*@Override
	@Transactional
	public List<Ordendespacho> buscarNombre(String nombJefeventas) {
		return dOrdendespacho.buscarNombre(nombJefeventas);
	}*/

	@Override
	@Transactional
	public List<Ordendespacho> buscarJefe(String nombJefeventas) {
		return dOrdendespacho.buscarJefe(nombJefeventas);
	}

	
	
}
