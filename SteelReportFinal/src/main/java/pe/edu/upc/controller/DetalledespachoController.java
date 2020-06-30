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


import pe.edu.upc.service.IProductoService;
import pe.edu.upc.service.IOrdendespachoService;
import pe.edu.upc.service.IDetalledespachoService;


import pe.edu.upc.model.Producto;
import pe.edu.upc.model.Ordendespacho;
import pe.edu.upc.model.Detalledespacho;
import pe.edu.upc.model.Ordencompra;

@Controller
@RequestMapping("/detalledespacho")
public class DetalledespachoController {

	@Autowired
	private IOrdendespachoService oService;
	
	@Autowired
	private IProductoService pService; 
	
	@Autowired
	private IDetalledespachoService dService;	
	
	@RequestMapping("/")
	public String irDetalledespacho(Map<String, Object> model) {
		model.put("listaDetalledespacho", dService.listar());
		return "industrial/listDetalledespacho";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{

		model.addAttribute("listaProductos", pService.listar());
		model.addAttribute("producto", new Producto());
		model.addAttribute("listaOrdendespacho", oService.listar());
		model.addAttribute("ordendespacho", new Ordendespacho());
		
		model.addAttribute("detalledespacho", new Detalledespacho());
		return "industrial/detalledespacho";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Detalledespacho objDetalledespacho, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaProductos", pService.listar());	
			model.addAttribute("listaOrdendespacho", oService.listar());	
			return "industrial/detalledespacho";
		}
		else {
				boolean flag = dService.insertar(objDetalledespacho);
				if (flag) {
					return "redirect:/detalledespacho/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/detalledespacho/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Detalledespacho objDetalledespacho, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/detalledespacho/listar";
		}
		else {
			boolean flag = dService.modificar(objDetalledespacho);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/detalledespacho/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/detalledespacho/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Detalledespacho> objDetalledespacho = dService.listarId(id);
		if (objDetalledespacho == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/detalledespacho/listar";
		}
		else {
		
			model.addAttribute("listaProductos", pService.listar());	
			model.addAttribute("listaOrdendespacho", oService.listar());		
			
			if (objDetalledespacho.isPresent())
				objDetalledespacho.ifPresent(o -> model.addAttribute("detalledespacho", o));						
			return "industrial/detalledespacho";
		}
	}
	// HASTA ACA VERIFICAR
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				dService.eliminar(id);
				model.put("listaDetalledespacho", dService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaDetalledespacho", dService.listar());
		}
		return "industrial/listDetalledespacho";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDetalledespacho", dService.listar());
		return "industrial/listDetalledespacho";
	}
	
	
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Detalledespacho detalledespacho) 
	throws ParseException
	{
		dService.listarId(detalledespacho.getIdDetalledespacho());
		return "industrial/listDetalledespacho";
	}
	
	@GetMapping("/detalledespachocomercial")
	public String listarcomercial(Model model) {
		try {
			model.addAttribute("detalledespacho", new Detalledespacho());
			model.addAttribute("listaDetalledespacho", dService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listDetalledespachocomercial";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Detalledespacho detalledespacho) 
			throws ParseException
	{
		List<Detalledespacho> listaDetalledespacho;
		detalledespacho.setNombdDespacho(detalledespacho.getNombdDespacho());
		listaDetalledespacho = dService.buscarNombre(detalledespacho.getNombdDespacho());
		
		if (listaDetalledespacho.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDetalledespacho", listaDetalledespacho);
		return "industrial/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("detalledespacho", new Detalledespacho());
		return "industrial/buscar";
	}
		
}

