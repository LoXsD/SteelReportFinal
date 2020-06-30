package pe.edu.upc.controller;
//js
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

import pe.edu.upc.model.Tipocliente;
import pe.edu.upc.service.ITipoclienteService;


@Controller
@RequestMapping("/tipocliente")
public class TipoclienteController {

	@Autowired
	private ITipoclienteService tService;
	
	@RequestMapping("/bienvenido")
	public String irTipoclienteBienvenido() {		
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irTipocliente(Map<String, Object> model) {
		model.put("listaTiposcliente", tService.listar());
		return "constructor/listTipocliente";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("tipocliente", new Tipocliente());
		return "constructor/tipocliente";
		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Tipocliente objTipocliente, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			return "tipocliente";
		}
		else {
				boolean flag = tService.insertar(objTipocliente);
				if (flag) {
					return "redirect:/tipocliente/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/tipocliente/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Tipocliente objTipocliente, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/tipocliente/listar";
		}
		else {
			boolean flag = tService.modificar(objTipocliente);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente :)");
				return "redirect:/tipocliente/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/tipocliente/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Tipocliente> objTipocliente = tService.listarId(id);
		if (objTipocliente == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/tipocliente/listar";
		}
		else {
			model.addAttribute("tipocliente", objTipocliente);
			return "constructor/tipocliente";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				tService.eliminar(id);
				model.put("listaTiposcliente", tService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaTiposcliente", tService.listar());
		}
		return "constructor/listTipocliente";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaTiposcliente", tService.listar());
		return "constructor/listTipocliente";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Tipocliente tipocliente) 
	throws ParseException
	{
		tService.listarId(tipocliente.getIdTipocliente());
		return "constructor/listTipocliente";
	}
	
	@GetMapping("/tipoclientecomercial")
	public String listar(Model model) {
		try {
			model.addAttribute("tipocliente", new Tipocliente());
			model.addAttribute("listaTiposcliente", tService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listTipoclientecomercial";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Tipocliente tipocliente) 
			throws ParseException
	{
		List<Tipocliente> listaTiposcliente;
		tipocliente.setNombTipocliente(tipocliente.getNombTipocliente());
		listaTiposcliente = tService.buscarNombre(tipocliente.getNombTipocliente());
		
		if (listaTiposcliente.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaTiposcliente", listaTiposcliente);
		return "constructor/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("tipocliente", new Tipocliente());
		return "constructor/buscar";
	}
		
}

