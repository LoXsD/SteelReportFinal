package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Obra;
import pe.edu.upc.repository.IObraRepository;
import pe.edu.upc.service.IObraService;


@Service
public class ObraServiceImpl implements IObraService {

	
	@Autowired
	private IObraRepository oObra;
	

	@Override
	@Transactional
	public boolean insertar(Obra obra) {
		Obra objObra = oObra.save(obra);
		if (objObra == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Obra obra) {
		boolean flag = false;
		try {
			oObra.save(obra);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idObra) {
		oObra.deleteById(idObra);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Obra> buscarId(int idObra) {
		return oObra.findById(idObra);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Obra> listarId(int idObra) {
		return oObra.findById(idObra);
	}

	@Override
	@Transactional
	public List<Obra> listar() {
		return oObra.findAll();
	}

	@Override
	@Transactional
	public List<Obra> buscarNombre(String nombreObra) {
		return oObra.buscarNombre(nombreObra);
	}

	@Override
	@Transactional
	public List<Obra> buscarCliente(String nombreCliente) {
		return oObra.buscarCliente(nombreCliente);
	}
	
}
