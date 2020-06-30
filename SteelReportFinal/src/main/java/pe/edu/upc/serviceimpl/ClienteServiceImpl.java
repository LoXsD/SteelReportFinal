package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Cliente;
import pe.edu.upc.repository.IClienteRepository;
import pe.edu.upc.service.IClienteService;


@Service
public class ClienteServiceImpl implements IClienteService {

	
	@Autowired
	private IClienteRepository cCliente;
	
	
	
	@Override
	@Transactional
	public boolean insertar(Cliente cliente) {
		Cliente objCliente = cCliente.save(cliente);
		if (objCliente == null)
			return false;
		else 
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Cliente cliente) {
		boolean flag = false;
		try {
			cCliente.save(cliente);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idCliente) {
		cCliente.deleteById(idCliente);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Cliente> buscarId(int idCliente) {
		return cCliente.findById(idCliente);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Cliente> listarId(int idCliente) {
		return cCliente.findById(idCliente);
	}

	@Override
	@Transactional
	public List<Cliente> listar() {
		return cCliente.findAll();
	}
	
	@Override
	@Transactional
	public List<Cliente> buscarNombre(String nombreCliente) {
		return cCliente.buscarNombre(nombreCliente);
	}

	@Override
	@Transactional
	public List<Cliente> buscarTipo(String nombTipocliente) {
		return cCliente.buscarTipo(nombTipocliente);
	}

	@Override
	@Transactional
	public List<Cliente> buscarJefe(String nombJefeventas) {
		return cCliente.buscarJefe(nombJefeventas);
	}

	
	
}
