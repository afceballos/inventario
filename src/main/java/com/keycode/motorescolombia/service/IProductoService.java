package com.keycode.motorescolombia.service;

import com.keycode.motorescolombia.dto.ProductoDTO;
import com.keycode.motorescolombia.dto.request.ProductoRqDTO;


import java.util.List;
/* Se listan los diferentes servicios en este interface para uso de los servicios que realizare el sistema*/
public interface IProductoService {
    List<ProductoDTO> obtenerProductos();
    ProductoDTO obtenerProductoPorId(Long id);
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoRqDTO productoRqDTO);
    boolean eliminarProducto(Long id);
}
