package com.gestion.productos.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.productos.entidades.Producto;
import com.gestion.productos.repositorio.ProductoRepositorio;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> listAll() {
        return productoRepositorio.findAll();
    }

    @Transactional
    public void save(Producto producto) {
        productoRepositorio.save(producto);
    }

    public Producto get(Long id) {
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Transactional
    public void delete(Long id) {
        if (!productoRepositorio.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
        productoRepositorio.deleteById(id);
    }
}

