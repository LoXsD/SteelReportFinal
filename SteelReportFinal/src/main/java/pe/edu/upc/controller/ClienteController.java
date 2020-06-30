package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pe.edu.upc.service.ITipoclienteService;
import pe.edu.upc.service.IJefeventasService;
import pe.edu.upc.service.IClienteService;


import pe.edu.upc.model.Tipocliente;
import pe.edu.upc.model.Jefeventas;
import pe.edu.upc.model.Cliente;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteService cService;
	
	@Autowired
	private ITipoclienteService tService; 
	
	@Autowired
	private IJefeventasService jService;	
	
	@RequestMapping("/bienvenido")
	public String irClienteBienvenido() {
		return "bienvenidoconstructor";
	}
	
	@RequestMapping("/")
	public String irCliente(Map<String, Object> model) {
		model.put("listaClientes", cService.listar());
		return "constructor/listCliente";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{

		model.addAttribute("listaTiposcliente", tService.listar());
		model.addAttribute("tipocliente", new Tipocliente());
		model.addAttribute("listaJefeventas", jService.listar());
		model.addAttribute("jefeventas", new Jefeventas());
		
		model.addAttribute("cliente", new Cliente());
		return "constructor/cliente";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Cliente objCliente, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaTiposcliente", tService.listar());	
			model.addAttribute("listaJefeventas", jService.listar());	
			return "cliente";
		}
		else {
				boolean flag = cService.insertar(objCliente);
				if (flag) {
					return "redirect:/cliente/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/cliente/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Cliente objCliente, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/cliente/listar";
		}
		else {
			boolean flag = cService.modificar(objCliente);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/cliente/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/cliente/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Cliente> objCliente = cService.listarId(id);
		if (objCliente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/cliente/listar";
		}
		else {
		
			model.addAttribute("listaTiposcliente", tService.listar());	
			model.addAttribute("listaJefeventas", jService.listar());		
			
			if (objCliente.isPresent())
				objCliente.ifPresent(o -> model.addAttribute("cliente", o));						
			return "constructor/cliente";
		}
	}
	// HASTA ACA VERIFICAR
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				cService.eliminar(id);
				model.put("listaClientes", cService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaClientes", cService.listar());
		}
		return "constructor/listCliente";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaClientes", cService.listar());
		return "constructor/listCliente";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Cliente cliente) 
	throws ParseException
	{
		cService.listarId(cliente.getIdCliente());
		return "constructor/listCliente";
	}
	

	@GetMapping("/clientecomercial")
	public String listar(Model model) {
		try {
			model.addAttribute("cliente", new Cliente());
			model.addAttribute("listaClientes", cService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listClientecomercial";
	}
	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Cliente cliente) 
			throws ParseException
	{
		List<Cliente> listaClientes;
		cliente.setNombreCliente(cliente.getNombreCliente());
		listaClientes = cService.buscarNombre(cliente.getNombreCliente());
		
		if (listaClientes.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaClientes", listaClientes);
		return "constructor/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "constructor/buscar";
	}
		
}

