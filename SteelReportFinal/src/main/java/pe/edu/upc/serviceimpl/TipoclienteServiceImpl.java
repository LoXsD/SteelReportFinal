package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Tipocliente;
import pe.edu.upc.repository.ITipoclienteRepository;
import pe.edu.upc.service.ITipoclienteService;

//s
@Service
public class TipoclienteServiceImpl implements ITipoclienteService {

	@Autowired
	private ITipoclienteRepository tTipocliente;
	
	@Override
	@Transactional
	public boolean insertar(Tipocliente tipocliente) {
		Tipocliente objTipocliente = tTipocliente.save(tipocliente);
		if (objTipocliente == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Tipocliente tipocliente) {
		boolean flag = false;
		try {
			tTipocliente.save(tipocliente);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idTipocliente) {
		tTipocliente.deleteById(idTipocliente);		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Tipocliente> listarId(int idTipocliente) {
		return tTipocliente.findById(idTipocliente);
	}

	@Override
	@Transactional
	public List<Tipocliente> listar() {
		return tTipocliente.findAll();
	}

	@Override
	@Transactional
	public List<Tipocliente> buscarNombre(String nombTipocliente) {
		return tTipocliente.buscarNombre(nombTipocliente);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Tipocliente> buscarId(int idTipocliente) {
		return tTipocliente.findById(idTipocliente);
	}

}
