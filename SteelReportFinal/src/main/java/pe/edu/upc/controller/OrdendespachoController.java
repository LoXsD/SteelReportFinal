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

import pe.edu.upc.service.IJefeventasService;
import pe.edu.upc.service.IOrdendespachoService;

import pe.edu.upc.model.Jefeventas;
import pe.edu.upc.model.Ordencompra;
import pe.edu.upc.model.Ordendespacho;

@Controller
@RequestMapping("/ordendespacho")
public class OrdendespachoController {

	@Autowired
	private IJefeventasService jeService;
	
	@Autowired
	private IOrdendespachoService odService;	
	
	@RequestMapping("/")
	public String irOrdendespacho(Map<String, Object> model) {
		model.put("listaOrdendespacho", odService.listar());
		return "industrial/listOrdendespacho";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{

		model.addAttribute("listaJefeventas", jeService.listar());
		model.addAttribute("jefeventas", new Jefeventas());
		model.addAttribute("ordendespacho", new Ordendespacho());
		return "industrial/ordendespacho";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Ordendespacho objOrdendespacho, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaJefeventas", jeService.listar());			
			return "industrial/ordendespacho";
		}
		else {
				boolean flag = odService.insertar(objOrdendespacho);
				if (flag) {
					return "redirect:/ordendespacho/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/ordendespacho/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Ordendespacho objOrdendespacho, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/ordendespacho/listar";
		}
		else {
			boolean flag = odService.modificar(objOrdendespacho);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/ordendespacho/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/ordendespacho/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Ordendespacho> objOrdendespacho = odService.listarId(id);
		if (objOrdendespacho == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/ordendespacho/listar";
		}
		else {
		
			model.addAttribute("listaJefeventas", jeService.listar());		
			
			if (objOrdendespacho.isPresent())
				objOrdendespacho.ifPresent(o -> model.addAttribute("ordendespacho", o));						
			return "industrial/ordendespacho";
		}
	}
	// HASTA ACA VERIFICAR
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				odService.eliminar(id);
				model.put("listaOrdendespacho", odService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaOrdendespacho", odService.listar());
		}
		return "industrial/listOrdendespacho";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaOrdendespacho", odService.listar());
		return "industrial/listOrdendespacho";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Ordendespacho ordendespacho) 
	throws ParseException
	{
		odService.listarId(ordendespacho.getIdOrdendespacho());
		return "industrial/listOrdendespacho";
	}
	
	
	@GetMapping("/ordendespachocomercial")
	public String listarcomercial(Model model) {
		try {
			model.addAttribute("ordendespacho", new Ordendespacho());
			model.addAttribute("listaOrdendespacho", odService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listOrdendespachocomercial";
	}
	
	/*@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Ordendespacho ordendespacho) 
			throws ParseException
	{
		List<Ordendespacho> listaOrdendespacho;
		ordendespacho.setFecDateOrdendespacho(ordendespacho.getFecDateOrdendespacho());
		listaOrdendespacho = odService.
		
		if (listaOrdendespacho.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaOrdendespacho", listaOrdendespacho);
		return "buscar";		
	}*/
	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("ordendespacho", new Ordendespacho());
		return "industrial/buscar";
	}
		
}

