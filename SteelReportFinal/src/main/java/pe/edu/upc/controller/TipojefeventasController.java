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

import pe.edu.upc.model.Tipojefeventas;
import pe.edu.upc.service.ITipojefeventasService;


@Controller
@RequestMapping("/tipojefeventas")
public class TipojefeventasController {

	@Autowired
	private ITipojefeventasService tService;
	
	@RequestMapping("/bienvenido")
	public String irTipojefeventasBienvenido() {		
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irTipojefeventas(Map<String, Object> model) {
		model.put("listaTiposjefeventas", tService.listar());
		return "comercial/listTipojefeventas";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("tipojefeventas", new Tipojefeventas());
		return "comercial/tipojefeventas";
		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Tipojefeventas objTipojefeventas, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			return "comercial/tipojefeventas";
		}
		else {
				boolean flag = tService.insertar(objTipojefeventas);
				if (flag) {
					return "redirect:/tipojefeventas/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/tipojefeventas/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Tipojefeventas objTipojefeventas, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/tipojefeventas/listar";
		}
		else {
			boolean flag = tService.modificar(objTipojefeventas);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente :)");
				return "redirect:/tipojefeventas/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/tipojefeventas/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Tipojefeventas> objTipojefeventas = tService.listarId(id);
		if (objTipojefeventas == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/tipojefeventas/listar";
		}
		else {
			model.addAttribute("tipojefeventas", objTipojefeventas);
			return "comercial/tipojefeventas";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				tService.eliminar(id);
				model.put("listaTiposjefeventas", tService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaTiposjefeventas", tService.listar());
		}
		return "comercial/listTipojefeventas";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaTiposjefeventas", tService.listar());
		return "comercial/listTipojefeventas";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Tipojefeventas tipojefeventas) 
	throws ParseException
	{
		tService.listarId(tipojefeventas.getIdTipojefeventas());
		return "comercial/listTipojefeventas";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Tipojefeventas tipojefeventas) 
			throws ParseException
	{
		List<Tipojefeventas> listaTiposjefeventas;
		tipojefeventas.setNombreTipo(tipojefeventas.getNombreTipo());
		listaTiposjefeventas = tService.buscarNombre(tipojefeventas.getNombreTipo());
		
		if (listaTiposjefeventas.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaTiposjefeventas", listaTiposjefeventas);
		return "comercial/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("tipojefeventas", new Tipojefeventas());
		return "comercial/buscar";
	}
		
}

