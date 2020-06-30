package pe.edu.upc.controller;

import java.text.ParseException;
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

import pe.edu.upc.service.IClienteService;
import pe.edu.upc.service.ISolicitudcompraService;

import pe.edu.upc.model.Solicitudcompra;
import pe.edu.upc.model.Cliente;


@Controller
@RequestMapping("/solicitudcompra")
public class SolicitudcompraController {

	@Autowired
	private IClienteService jeService;
	
	@Autowired
	private ISolicitudcompraService orService;	
	
	@RequestMapping("/bienvenido")
	public String irSolicitudCompraBienvenido() {
		return "bienvenidocliente";
	}
	@RequestMapping("/")
	public String irOrdencompra(Map<String, Object> model) {
		model.put("listaSolicitudcompra", orService.listar());
		return "cliente/listSolicitudcompra";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{

		model.addAttribute("listaClientes",jeService.listar());
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("solicitudcompra", new Solicitudcompra());
		return "cliente/solicitudcompra";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Solicitudcompra objSolicitudcompra, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaClientes", jeService.listar());			
			return "cliente/solicitudcompra";
		}
		else {
				boolean flag = orService.insertar(objSolicitudcompra);
				if (flag) {
					return "redirect:/solicitudcompra/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/solicitudcompra/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Solicitudcompra objSolicitudcompra, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/solicitudcompra/listar";
		}
		else {
			boolean flag = orService.modificar(objSolicitudcompra);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/solicitudcompra/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/solicitudcompra/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Solicitudcompra> objSolicitudcompra = orService.listarId(id);
		if (objSolicitudcompra == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/solicitudcompra/listar";
		}
		else {
		
			model.addAttribute("listaClientes", jeService.listar());		
			
			if (objSolicitudcompra.isPresent())
				objSolicitudcompra.ifPresent(o -> model.addAttribute("solicitudcompra", o));						
			return "cliente/solicitudcompra";
		}
	}
	// HASTA ACA VERIFICAR
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				orService.eliminar(id);
				model.put("listaSolicitudcompra", orService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaSolicitudcompra", orService.listar());
		}
		return "cliente/listSolicitudcompra";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSolicitudcompra", orService.listar());
		return "cliente/listSolicitudcompra";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Solicitudcompra solicitudcompra) 
	throws ParseException
	{
		orService.listarId(solicitudcompra.getIdSolicitudcompra());
		return "cliente/listSolicitudcompra";
	}
	
	
	@GetMapping("/solicitudcompracomercial")
	public String listar(Model model) {
		try {
			model.addAttribute("solicitudcompra", new Solicitudcompra());
			model.addAttribute("listaSolicitudcompra", orService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listSolicitudcompracomercial";
	}
	
	/*@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Ordencompra ordencompra) 
			throws ParseException
	{
		List<Ordencompra> listaOrdencompra;
		ordencompra.setNombJefeventas(jefeventas.getNombJefeventas());
		listaJefeventas = jService.buscarNombre(jefeventas.getNombJefeventas());
		
		if (listaJefeventas.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaJefeventas", listaJefeventas);
		return "buscar";
	}*/
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("solicitudcompra", new Solicitudcompra());
		return "cliente/buscar";
	}
		
}

