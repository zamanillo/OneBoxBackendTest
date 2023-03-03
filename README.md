# OneBoxBackendTest 

El reto pedía una aplicación que creara carritos de la compra donde poder añadir productos, que pudiera consultar los carritos y los pudiera eliminar. También un método para que se eliminara pasado 10 minutos de forma automática.
He optado por hacer una API REST, con un servicio-controlador para realizar estas tareas.
Al pensar que no se podía utilizar base de datos he utilizado una simple lista de carritos estatica y publica para gestionar estos.
Para eliminar los carritos de forma automática he utilizado la clase TimerTask ya permite programar una tarea para su ejecución en un momento específico (en este caso 10 minutos).



# Arquitectura

Utilizo una estructura básica con capa modelo entidad, servicio y controlador.
No he creado una DTO para transmitir la información de forma independiente a nuestro modelo de datos (entidad) por no verlo necesario.Tampoco he visto necesario en este caso crear interficies por cada uno de los servicios.
La estructura de carpetas es la siguiente:

![image](https://user-images.githubusercontent.com/123003363/222770189-5edf299b-4ada-4d06-bfdc-d52bc1baaacd.png)


# End-points

He creado 4 endpoints:

Un POST para crear el carrito (con id automático) con un requestBody con el producto, en caso de no haber RequestBody se crea el carrito pero no se añade producto a la lista.

http://localhost:9006/backendTest/api/v1/cart

{
    "id":3,
    "description":"rotulador",
    "amount":4
}

Un PUT para poder añadir productos al carrito. De la misma manera se añaden con un RequestBody y con un PathVariable para seleccionar el carrito. En caso de existir el objeto en el carrito se incrementa la cantidad.

http://localhost:9006/backendTest/api/v1/cart/2
{
    "id":1,
    "description":"lapiz",
    "amount":2
}

Un GET para obtener los datos del carrito (id y productos) 

http://localhost:9006/backendTest/api/v1/cart/0

Un DELETE para eliminar el carrito, PathVariable para designar el carrito a eliminar. 

http://localhost:9006/backendTest/api/v1/cart/1


# Pruebas con Postman

Un ejemplo de las pruebas realizadas con  Postman:


![image](https://user-images.githubusercontent.com/123003363/222769909-5fc7b166-7359-4813-b0fc-e12a93efbfc2.png)


# Posibles mejoras

No me ha dado tiempo ha realizar los test unitarios con JUnit.

He tenido problemas a la hora de configurar Swagger para documentar los endpoints.

Utilizar H2 ya que reside en memoria y siempre es mejor que una simple lista estática.
