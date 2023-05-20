# Inventario
 Taller diplomado, inventario de producto, microservicio
 
#Endpoints a utilizar

○ GET /productos: Retorna la lista de todos los productos en el inventario

○ GET /productos/{id}: Retorna los detalles de un producto específico. 

○ POST /productos: Crea un nuevo producto en el inventario y retorna el producto creado en un JSON

○ PUT /productos/{id}: Actualiza los detalles de un producto existente. solo se le pasa el id del producto y el json con los cambios

  JSON:
  
  {
  
      "nombre": "nombre producto",
      
      "precio": 2000,
      
      "cantidad": 100
      
  }

○ DELETE /productos/{id}: Elimina un producto del inventario con tan solo pasarle el id dl producto que se desea eliminar.

#Base de Datos | Maria DB

○ Config en .yml:

    datasource:
    
      url: jdbc:mariadb://127.0.0.1:3308/productos
      
      username: root
      
      password: finanz
      
      driver-class-name: org.mariadb.jdbc.Driver
