package com.keycode.motorescolombia.dto.request;

import lombok.Data;

/*configuracion DTO Request para la actualizacion del producto, sin necesidad que se envie el id del producto*/
@Data
public class ProductoRqDTO {

    private String nombre;
    private double precio;
    private int cantidad;

}
