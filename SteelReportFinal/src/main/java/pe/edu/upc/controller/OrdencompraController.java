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
import pe.edu.upc.service.IOrdencompraService;

import pe.edu.upc.model.Jefeventas;
import pe.edu.upc.model.Ordencompra;
import pe.edu.upc.model.Producto;

@Controller
@RequestMapping("/ordencompra")
public class OrdencompraController {

	@Autowired
	private IJefeventasService jeService;
	
	@Autowired
	private IOrdencompraService orService;	
	
	@RequestMapping("/")
	public String irOrdencompra(Map<String, Object> model) {
		model.put("listaOrdencompra", orService.listar());
		return "industrial/listOrdencompra";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{

		model.addAttribute("listaJefeventas",jeService.listar());
		model.addAttribute("jefeventas", new Jefeventas());
		model.addAttribute("ordencompra", new Ordencompra());
		return "industrial/ordencompra";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Ordencompra objOrdencompra, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaJefeventas", jeService.listar());			
			return "industrial/ordencompra";
		}
		else {
				boolean flag = orService.insertar(objOrdencompra);
				if (flag) {
					return "redirect:/ordencompra/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/ordencompra/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Ordencompra objOrdencompra, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/ordencompra/listar";
		}
		else {
			boolean flag = orService.modificar(objOrdencompra);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/ordencompra/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/ordencompra/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Ordencompra> objOrdencompra = orService.listarId(id);
		if (objOrdencompra == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/ordencompra/listar";
		}
		else {
		
			model.addAttribute("listaJefeventas", jeService.listar());		
			
			if (objOrdencompra.isPresent())
				objOrdencompra.ifPresent(o -> model.addAttribute("ordencompra", o));						
			return "industrial/ordencompra";
		}
	}
	// HASTA ACA VERIFICAR
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				orService.eliminar(id);
				model.put("listaOrdencompra", orService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaOrdencompra", orService.listar());
		}
		return "industrial/listOrdencompra";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaOrdencompra", orService.listar());
		return "industrial/listOrdencompra";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Ordencompra ordencompra) 
	throws ParseException
	{
		orService.listarId(ordencompra.getIdOrdencompra());
		return "industrial/listOrdencompra";
	}
	
	@GetMapping("/ordencompracomercial")
	public String listarcomercial(Model model) {
		try {
			model.addAttribute("ordencompra", new Ordencompra());
			model.addAttribute("listaOrdencompra", orService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listOrdencompracomercial";
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
		model.addAttribute("ordencompra", new Ordencompra());
		return "industrial/buscar";
	}
		
}

