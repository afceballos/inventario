package com.keycode.motorescolombia.service.impl;

import com.keycode.motorescolombia.dto.ProductoDTO;
import com.keycode.motorescolombia.dto.request.ProductoRqDTO;
import com.keycode.motorescolombia.jpa.entity.Producto;
import com.keycode.motorescolombia.jpa.repository.ProductoRepository;
import com.keycode.motorescolombia.service.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ProductoService implements IProductoService {
    private final ProductoRepository productoRepository;
    @Autowired
    private final ModelMapper modelMapper;


    public ProductoService(ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }

    //Servicio encargado de retornar todos los procutos
    @Override
    public List<ProductoDTO> obtenerProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    //Servicio encargado de retornar un producto en especifico, se debe pasar el id
    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        return productoOptional.map(this::convertirAProductoDTO).orElse(null);
    }

    /*Servicio para crear producto el cual resive un JSON Ejemplo:
    {
        "id": 4,
        "nombre": "nuevo producto",
        "precio": 20000.0,
        "cantidad": 150
    }
    *
    * El producto llega como un DTO y le hacemos un model maper segun la clase del producto para poder ser actualizado
    *
    */
    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = convertirAProductoEntity(productoDTO);
        Producto nuevoProducto = productoRepository.save(producto);
        return convertirAProductoDTO(nuevoProducto);
    }

    /*Servicio para actualizar producto el cual resive un JSON Ejemplo:
    {
        "nombre": "nuevo producto",
        "precio": 20000.0,
        "cantidad": 150
    }
    * Y se le entrega por la url el id del producto que se desea actualizar
    *
    * El producto llega como un DTO y le hacemos un model maper segun la clase del producto para poder ser actualizado
    *
    */
    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoRqDTO productoRqDTO) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            modelMapper.map(productoRqDTO, producto);
            Producto productoActualizado = productoRepository.save(producto);
            return convertirAProductoDTO(productoActualizado);
        } else {
            throw new RuntimeException(
                    String.format("El producto con id %s no se encuentra en el inventario", id));
        }



    }

    /*Servico  encargado de elminar producto por medio del id enviado desde la url*/
    @Override
    public boolean eliminarProducto(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            productoRepository.delete(productoOptional.get());
            return true;
        } else {
            return false;
        }
    }

    // Convertir la clase de producto en la de ProductoDTO
    private ProductoDTO convertirAProductoDTO(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }

    // Convertir la clase de ProductoDTO en la de Productopara efectos de creacion
    private Producto convertirAProductoEntity(ProductoDTO productoDTO) {
        return modelMapper.map(productoDTO, Producto.class);
    }
}
