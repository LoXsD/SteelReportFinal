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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.service.ITipojefeventasService;
import pe.edu.upc.service.IJefeventasService;

import pe.edu.upc.model.Tipojefeventas;
import pe.edu.upc.model.Jefeventas;

@Controller
@RequestMapping("/jefeventas")
public class JefeventasController {

	@Autowired
	private ITipojefeventasService tService;
	
	@Autowired
	private IJefeventasService jService;	
	
	@RequestMapping("/")
	public String irJefeventas(Map<String, Object> model) {
		model.put("listaJefeventas", jService.listar());
		return "comercial/listJefeventas";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{

		model.addAttribute("listaTiposjefeventas", tService.listar());
		model.addAttribute("tipojefe", new Tipojefeventas());
		model.addAttribute("jefeventas", new Jefeventas());
		return "comercial/jefeventas";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Jefeventas objJefeventas, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaTiposjefeventas", tService.listar());			
			return "jefeventas";
		}
		else {
				boolean flag = jService.insertar(objJefeventas);
				if (flag) {
					return "redirect:/jefeventas/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/jefeventas/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Jefeventas objJefeventas, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/jefeventas/listar";
		}
		else {
			boolean flag = jService.modificar(objJefeventas);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/jefeventas/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/jefeventas/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Jefeventas> objJefeventas = jService.listarId(id);
		if (objJefeventas == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/jefeventas/listar";
		}
		else {
		
			model.addAttribute("listaTiposjefeventas", tService.listar());		
			
			if (objJefeventas.isPresent())
				objJefeventas.ifPresent(o -> model.addAttribute("jefeventas", o));						
			return "comercial/jefeventas";
		}
	}
	// HASTA ACA VERIFICAR
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				jService.eliminar(id);
				model.put("listaJefeventas", jService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaJefeventas", jService.listar());
		}
		return "comercial/listJefeventas";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaJefeventas", jService.listar());
		return "comercial/listJefeventas";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Jefeventas jefeventas) 
	throws ParseException
	{
		jService.listarId(jefeventas.getIdJefeventas());
		return "comercial/listJefeventas";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Jefeventas jefeventas) 
			throws ParseException
	{
		List<Jefeventas> listaJefeventas;
		jefeventas.setNombJefeventas(jefeventas.getNombJefeventas());
		listaJefeventas = jService.buscarNombre(jefeventas.getNombJefeventas());
		
		if (listaJefeventas.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaJefeventas", listaJefeventas);
		return "comercial/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("jefeventas", new Jefeventas());
		return "comercial/buscar";
	}
		
}

