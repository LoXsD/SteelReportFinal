package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Ordencompra;
import pe.edu.upc.repository.IOrdencompraRepository;
import pe.edu.upc.service.IOrdencompraService;


@Service
public class OrdencompraServiceImpl implements IOrdencompraService {

	
	@Autowired
	private IOrdencompraRepository dOrdencompra;
	
	
	
	@Override
	@Transactional
	public boolean insertar(Ordencompra ordencompra) {
		Ordencompra objOrdencompra = dOrdencompra.save(ordencompra);
		if (objOrdencompra == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Ordencompra ordencompra) {
		boolean flag = false;
		try {
			dOrdencompra.save(ordencompra);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idOrdencompra) {
		dOrdencompra.deleteById(idOrdencompra);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Ordencompra> buscarId(int idOrdencompra) {
		return dOrdencompra.findById(idOrdencompra);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Ordencompra> listarId(int idOrdencompra) {
		return dOrdencompra.findById(idOrdencompra);
	}

	@Override
	@Transactional
	public List<Ordencompra> listar() {
		return dOrdencompra.findAll();
	}

	/*@Override
	@Transactional
	public List<Ordencompra> buscarNombre(String nombJefeventa) {
		return dOrdencompra.buscarNombre(nombJefeventas);
	}*/

	@Override
	@Transactional
	public List<Ordencompra> buscarJefe(String nombJefeventas) {
		return dOrdencompra.buscarJefe(nombJefeventas);
	}

	
	
}
