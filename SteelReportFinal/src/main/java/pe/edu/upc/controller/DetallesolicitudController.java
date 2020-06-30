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

import pe.edu.upc.service.ISolicitudcompraService;
import pe.edu.upc.service.IProductoService;
import pe.edu.upc.service.IDetallesolicitudService;

import pe.edu.upc.model.Solicitudcompra;
import pe.edu.upc.model.Producto;
import pe.edu.upc.model.Detallesolicitud;

@Controller
@RequestMapping("/detallesolicitud")
public class DetallesolicitudController {

	@Autowired
	private ISolicitudcompraService sService;
	@Autowired
	private IProductoService ptService;
	@Autowired
	private IDetallesolicitudService dService;	
	
	@RequestMapping("/")
	public String irDetallecompra(Map<String, Object> model) {
		model.put("listaDetallesolicitud", dService.listar());
		return "cliente/listDetallesolicitud";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) 
	{
		model.addAttribute("listaProductos", ptService.listar());
		model.addAttribute("listaSolicitudcompra", sService.listar());
		model.addAttribute("producto", new Producto());
		model.addAttribute("solicitudcompra", new Solicitudcompra());
		model.addAttribute("detallesolicitud", new Detallesolicitud());
		return "cliente/detallesolicitud";		
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Detallesolicitud objDetallesolicitud, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaProductos", ptService.listar());
			model.addAttribute("listaSolicitudcompra", sService.listar());			
			return "cliente/detallesolicitud";
		}
		else {
				boolean flag = dService.insertar(objDetallesolicitud);
				if (flag) {
					return "redirect:/detallesolicitud/listar";
				}
				else {
					model.addAttribute("mensaje", "Ocurrio un roche");
					return "redirect:/detallesolicitud/irRegistrar";
				}
			}
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Detallesolicitud objDetallesolicitud, BindingResult binRes, Model model,
			RedirectAttributes objRedir) throws ParseException
	{
		if (binRes.hasErrors()) {
			return "redirect:/detallesolicitud/listar";
		}
		else {
			boolean flag = dService.modificar(objDetallesolicitud);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "Se actualizo correctamente");
				return "redirect:/detallesolicitud/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:/detallesolicitud/irRegistrar";
			}			
		}		
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException 
	{
		Optional<Detallesolicitud> objDetallesolicitud = dService.listarId(id);
		if (objDetallesolicitud == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un roche");
			return "redirect:/detallesolicitud/listar";
		}
		else {
			model.addAttribute("listaProductos", ptService.listar());
			model.addAttribute("listaSolicitudcompra", sService.listar());		
			
			if (objDetallesolicitud.isPresent())
				objDetallesolicitud.ifPresent(o -> model.addAttribute("detallesolicitud", o));						
			return "cliente/detallesolicitud";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id > 0) {
				dService.eliminar(id);
				model.put("listaDetallesolicitud", dService.listar());
			}			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un roche");
			model.put("listaDetallesolicitud", dService.listar());
		}
		return "cliente/listDetallesolicitud";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDetallesolicitud", dService.listar());
		return "cliente/listDetallesolicitud";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Detallesolicitud detallesolicitud) 
	throws ParseException
	{
		dService.listarId(detallesolicitud.getIdDetallesolicitud());
		return "cliente/listDetallesolicitud";
	}
	
	@GetMapping("/detallesolicitudcomercial")
	public String listar(Model model) {
		try {
			model.addAttribute("detallesolicitud", new Detallesolicitud());
			model.addAttribute("listaDetallesolicitud", dService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/comercial/listDetallesolicitudcomercial";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Detallesolicitud detallesolicitud) 
			throws ParseException
	{
		List<Detallesolicitud> listaDetallesolicitud;
		detallesolicitud.setCantiDetallesolicitud(detallesolicitud.getCantiDetallesolicitud());
		listaDetallesolicitud = dService.buscarCantidad(detallesolicitud.getCantiDetallesolicitud());
		
		if (listaDetallesolicitud.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDetallesolicitud", listaDetallesolicitud);
		return "cliente/buscar";		
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("detallesolicitud", new Detallesolicitud());
		return "cliente/buscar";
	}
		
}

