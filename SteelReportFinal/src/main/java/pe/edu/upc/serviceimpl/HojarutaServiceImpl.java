package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Hojaruta;
import pe.edu.upc.repository.IHojarutaRepository;
import pe.edu.upc.service.IHojarutaService;


@Service
public class HojarutaServiceImpl implements IHojarutaService {

	
	@Autowired
	private IHojarutaRepository dHojaruta;
	
	
	
	@Override
	@Transactional
	public boolean insertar(Hojaruta hojaruta) {
		Hojaruta objHojaruta = dHojaruta.save(hojaruta);
		if (objHojaruta == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Hojaruta hojaruta) {
		boolean flag = false;
		try {
			dHojaruta.save(hojaruta);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idHojaruta) {
		dHojaruta.deleteById(idHojaruta);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Hojaruta> buscarId(int idHojaruta) {
		return dHojaruta.findById(idHojaruta);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Hojaruta> listarId(int idHojaruta) {
		return dHojaruta.findById(idHojaruta);
	}

	@Override
	@Transactional
	public List<Hojaruta> listar() {
		return dHojaruta.findAll();
	}

	@Override
	@Transactional
	public List<Hojaruta> buscarPresu(String presuHojaruta) {
		return dHojaruta.buscarPresu(presuHojaruta);
	}

	@Override
	@Transactional
	public List<Hojaruta> buscarJefe(String nombJefeventas) {
		return dHojaruta.buscarJefe(nombJefeventas);
	}

	
}
