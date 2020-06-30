package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Jefeventas;
import pe.edu.upc.repository.IJefeventasRepository;
import pe.edu.upc.service.IJefeventasService;


@Service
public class JefeventasServiceImpl implements IJefeventasService {

	
	@Autowired
	private IJefeventasRepository dJefeventas;
	
	
	
	@Override
	@Transactional
	public boolean insertar(Jefeventas jefeventas) {
		Jefeventas objJefeventas = dJefeventas.save(jefeventas);
		if (objJefeventas == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Jefeventas jefeventas) {
		boolean flag = false;
		try {
			dJefeventas.save(jefeventas);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idJefeventas) {
		dJefeventas.deleteById(idJefeventas);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Jefeventas> buscarId(int idJefeventas) {
		return dJefeventas.findById(idJefeventas);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Jefeventas> listarId(int idJefeventas) {
		return dJefeventas.findById(idJefeventas);
	}

	@Override
	@Transactional
	public List<Jefeventas> listar() {
		return dJefeventas.findAll();
	}

	@Override
	@Transactional
	public List<Jefeventas> buscarNombre(String nombJefeventas) {
		return dJefeventas.buscarNombre(nombJefeventas);
	}

	@Override
	@Transactional
	public List<Jefeventas> buscarTipo(String nombreTipo) {
		return dJefeventas.buscarTipo(nombreTipo);
	}

	
	
}
