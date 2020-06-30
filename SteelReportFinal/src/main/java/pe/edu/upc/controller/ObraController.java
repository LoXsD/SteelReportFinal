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

import pe.edu.upc.service.IClienteService;
import pe.edu.upc.service.IObraService;

import pe.edu.upc.model.Cliente;
import pe.edu.upc.model.Obra;

@Controller
@RequestMapping("/obra")
public class ObraController {

	@Autowired
	private IClienteService cService;
	
	@Autowired
	private IObraService jService;	
	
	@RequestMapping("/")
	public String irObra(Map<String, Object> model) {
		model.put("listaObras", jService.listar());
		return "listObra";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{

		model.addAttribute("listaClientes", cService.listar());
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("obra", new Obra());
		return "constructor/obra";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Obra objObra, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaClientes", cService.listar());			
			return "constructor/obra";
		}
		else {
				boolean flag = jService.insertar(objObra);
				if (flag) {
					return "redirect:/obra/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/obra/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Obra objObra, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/obra/listar";
		}
		else {
			boolean flag = jService.modificar(objObra);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/obra/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/obra/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Obra> objObra = jService.listarId(id);
		if (objObra == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/obra/listar";
		}
		else {
		
			model.addAttribute("listaClientes", cService.listar());		
			
			if (objObra.isPresent())
				objObra.ifPresent(o -> model.addAttribute("obra", o));						
			return "constructor/obra";
		}
	}
	// HASTA ACA VERIFICAR
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				jService.eliminar(id);
				model.put("listaClientes", jService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaObras", jService.listar());
		}
		return "constructor/listObra";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaObras", jService.listar());
		return "constructor/listObra";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Obra obra) 
	throws ParseException
	{
		jService.listarId(obra.getIdObra());
		return "constructor/listObra";
	}
	
	
	@GetMapping("/obracomercial")
	public String listar(Model model) {
		try {
			model.addAttribute("obra", new Obra());
			model.addAttribute("listaObras", jService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listObracomercial";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Obra obra) 
			throws ParseException
	{
		List<Obra> listaObras;
		obra.setNombreObra(obra.getNombreObra());
		listaObras = jService.buscarNombre(obra.getNombreObra());
		
		if (listaObras.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaObras", listaObras);
		return "constructor/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("obra", new Obra());
		return "constructor/buscar";
	}
		
}

