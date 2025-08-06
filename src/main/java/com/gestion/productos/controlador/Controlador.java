package com.gestion.productos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestion.productos.entidades.Producto;
import com.gestion.productos.servicio.ProductoServicio;

import java.util.List;

@Controller
public class Controlador {

	@Autowired
	private ProductoServicio productoServicio;

	@GetMapping("/")
	public String verPaginaInicio(Model modelo) {
	    List<Producto> listaProductos = productoServicio.listAll();
	    modelo.addAttribute("listaProductos", listaProductos);
	    return "index"; // Asegúrate de que este nombre coincida con el archivo index.html
	}

	

	@GetMapping("/nuevo")
	public String mostrarFormularioDeRegistrarProducto(Model modelo) {
		Producto producto = new Producto();
		modelo.addAttribute("producto", producto);
		return "nuevo_producto";
	}

	@PostMapping("/guardar")
	public String guardarProducto(@ModelAttribute("producto") Producto producto,
			RedirectAttributes redirectAttributes) {
		productoServicio.save(producto);
		redirectAttributes.addFlashAttribute("mensajeExito", "Producto guardado exitosamente");
		return "redirect:/";
	}

	@GetMapping("/editar/{id}")
	public ModelAndView mostrarFormularioDeEditarProducto(@PathVariable("id") Long id) {
		ModelAndView modelo = new ModelAndView("editar_producto");
		Producto producto = productoServicio.get(id);
		modelo.addObject("producto", producto);
		return modelo;
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		productoServicio.delete(id);
		redirectAttributes.addFlashAttribute("mensajeExito", "Producto eliminado correctamente");
		return "redirect:/";
	}
}
