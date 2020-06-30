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

import pe.edu.upc.service.IOrdencompraService;
import pe.edu.upc.service.IProductoService;
import pe.edu.upc.service.IDetallecompraService;

import pe.edu.upc.model.Ordencompra;
import pe.edu.upc.model.Producto;
import pe.edu.upc.model.Detallecompra;

@Controller
@RequestMapping("/detallecompra")
public class DetallecompraController {

	@Autowired
	private IOrdencompraService oService;
	@Autowired
	private IProductoService ptService;
	@Autowired
	private IDetallecompraService dService;	
	
	@RequestMapping("/")
	public String irDetallecompra(Map<String, Object> model) {
		model.put("listaDetallecompra", dService.listar());
		return "industrial/listDetallecompra";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("listaProductos", ptService.listar());
		model.addAttribute("listaOrdencompra", oService.listar());
		model.addAttribute("producto", new Producto());
		model.addAttribute("ordencompra", new Ordencompra());
		model.addAttribute("detallecompra", new Detallecompra());
		return "industrial/detallecompra";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Detallecompra objDetallecompra, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaProductos", ptService.listar());
			model.addAttribute("listaOrdencompra", oService.listar());			
			return "industrial/detallecompra";
		}
		else {
				boolean flag = dService.insertar(objDetallecompra);
				if (flag) {
					return "redirect:/detallecompra/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/detallecompra/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Detallecompra objDetallecompra, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/detallecompra/listar";
		}
		else {
			boolean flag = dService.modificar(objDetallecompra);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/detallecompra/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/detallecompra/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Detallecompra> objDetallecompra = dService.listarId(id);
		if (objDetallecompra == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/detallecompra/listar";
		}
		else {
			model.addAttribute("listaProductos", ptService.listar());
			model.addAttribute("listaOrdencompra", oService.listar());		
			
			if (objDetallecompra.isPresent())
				objDetallecompra.ifPresent(o -> model.addAttribute("detallecompra", o));						
			return "industrial/detallecompra";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				dService.eliminar(id);
				model.put("listaDetallecompra", dService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaDetallecompra", dService.listar());
		}
		return "industrial/listDetallecompra";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDetallecompra", dService.listar());
		return "industrial/listDetallecompra";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Detallecompra detallecompra) 
	throws ParseException
	{
		dService.listarId(detallecompra.getIdDetallecompra());
		return "industrial/listDetallecompra";
	}
	
	@GetMapping("/detallecompracomercial")
	public String listar(Model model) {
		try {
			model.addAttribute("detallecompra", new Detallecompra());
			model.addAttribute("listaDetallecompra", dService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listDetallecompracomercial";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Detallecompra detallecompra) 
			throws ParseException
	{
		List<Detallecompra> listaDetallecompra;
		detallecompra.setCantiDetallecompra(detallecompra.getCantiDetallecompra());
		listaDetallecompra = dService.buscarCantidad(detallecompra.getCantiDetallecompra());
		
		if (listaDetallecompra.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDetallecompra", listaDetallecompra);
		return "industrial/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("detallecompra", new Detallecompra());
		return "industrial/buscar";
	}
		
}

