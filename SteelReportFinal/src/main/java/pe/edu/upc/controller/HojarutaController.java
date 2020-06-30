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
import pe.edu.upc.service.IHojarutaService;

import pe.edu.upc.model.Jefeventas;
import pe.edu.upc.model.Ordencompra;
import pe.edu.upc.model.Hojaruta;
//ACA ES DONDE TENGO QUE EDITAR
@Controller
@RequestMapping("/hojaruta")
public class HojarutaController {

	@Autowired
	private IJefeventasService jeService;
	
	@Autowired
	private IHojarutaService hService;	
	
	@RequestMapping("/")
	public String irHojaruta(Map<String, Object> model) {
		model.put("listaHojaruta", hService.listar());
		return "industrial/listHojaruta";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{

		model.addAttribute("listaJefeventas", jeService.listar());
		model.addAttribute("jefeventas", new Jefeventas());
		model.addAttribute("hojaruta", new Hojaruta());
		return "industrial/hojaruta";		
	}
	
	
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Hojaruta objHojaruta, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaJefeventas", jeService.listar());			
			return "industrial/hojaruta";
		}
		else {
				boolean flag = hService.insertar(objHojaruta);
				if (flag) {
					return "redirect:/hojaruta/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/hojaruta/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Hojaruta objHojaruta, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/hojaruta/listar";
		}
		else {
			boolean flag = hService.modificar(objHojaruta);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/hojaruta/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/hojaruta/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Hojaruta> objHojaruta = hService.listarId(id);
		if (objHojaruta == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/hojaruta/listar";
		}
		else {
		
			model.addAttribute("listaJefeventas", jeService.listar());		
			
			if (objHojaruta.isPresent())
				objHojaruta.ifPresent(o -> model.addAttribute("hojaruta", o));						
			return "industrial/hojaruta";
		}
	}
	// HASTA ACA VERIFICAR
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				hService.eliminar(id);
				model.put("listaHojaruta", hService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaHojaruta", hService.listar());
		}
		return "industrial/listHojaruta";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaHojaruta", hService.listar());
		return "industrial/listHojaruta";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Hojaruta hojaruta) 
	throws ParseException
	{
		hService.listarId(hojaruta.getIdHojaruta());
		return "industrial/listHojaruta";
	}
	
	@GetMapping("/hojarutacomercial")
	public String listarcomercial(Model model) {
		try {
			model.addAttribute("hojaruta", new Hojaruta());
			model.addAttribute("listaHojaruta", hService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listHojarutacomercial";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Hojaruta hojaruta) 
			throws ParseException
	{
		List<Hojaruta> listaHojaruta;
		hojaruta.setDescrHojaruta(hojaruta.getDescrHojaruta());
		listaHojaruta = hService.buscarJefe(hojaruta.getDescrHojaruta());
		
		if (listaHojaruta.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaHojaruta", listaHojaruta);
		return "industrial/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("hojaruta", new Hojaruta());
		return "industrial/buscar";
	}
		
}

