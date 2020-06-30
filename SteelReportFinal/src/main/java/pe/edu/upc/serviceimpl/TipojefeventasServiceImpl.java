package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Tipojefeventas;
import pe.edu.upc.repository.ITipojefeventasRepository;
import pe.edu.upc.service.ITipojefeventasService;


@Service
public class TipojefeventasServiceImpl implements ITipojefeventasService {

	@Autowired
	private ITipojefeventasRepository tTipojefeventas;
	
	@Override
	@Transactional
	public boolean insertar(Tipojefeventas tipojefeventas) {
		Tipojefeventas objTipojefeventas = tTipojefeventas.save(tipojefeventas);
		if (objTipojefeventas == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Tipojefeventas tipojefeventas) {
		boolean flag = false;
		try {
			tTipojefeventas.save(tipojefeventas);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idTipojefeventas) {
		tTipojefeventas.deleteById(idTipojefeventas);		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Tipojefeventas> listarId(int idTipojefeventas) {
		return tTipojefeventas.findById(idTipojefeventas);
	}

	@Override
	@Transactional
	public List<Tipojefeventas> listar() {
		return tTipojefeventas.findAll();
	}

	@Override
	@Transactional
	public List<Tipojefeventas> buscarNombre(String nombreTipo) {
		return tTipojefeventas.buscarNombre(nombreTipo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Tipojefeventas> buscarId(int idTipojefeventas) {
		return tTipojefeventas.findById(idTipojefeventas);
	}
}
