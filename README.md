# PlaceApp
PlaceApp Aplicación para reservar espacios

El proyecto consiste en crear un sistema de reservas de espacios, 
focalizado en comunidades de vecinos con un tamaño contenido. 
Se trata de una aplicación web en la que se permitirá interactuar a dos tipos de usuarios principalmente: propietarios y clientes. 


## Requisitos previos

- MySQL Workbench 8 instalado en el sistema.
- Eclipse IDE con entorno de desarrollo de Spring Boot.
- Node.js v20.12.2 (LTS).

## Pasos para iniciar la aplicación

### Configuración de la base de datos

1. Abre MySQL Workbench en el sistema.
2. Ejecuta el script del repositorio para crear la base de datos y las tablas necesarias para la aplicación.

### Inicio del servidor Backend con Eclipse

1. Abre Eclipse en el sistema.
2. Importa el proyecto de la aplicación.
3. Ejecuta la aplicación Spring Boot desde Eclipse. Esto iniciará el servidor Backend.

### Configuración y ejecución del cliente web

1. Abre un terminal o línea de comandos en el sistema.
2. Navega hasta el directorio del proyecto del cliente web, este es `Placeapp_app`.
3. Ejecuta el comando `npm install` para instalar todas las dependencias del proyecto.
4. Una vez completada la instalación de las dependencias, ejecuta el siguiente comando `npm run dev` para iniciar el servidor.
5. Después de unos segundos, el servidor se iniciará y se podrá acceder a la aplicación web en el navegador predeterminado.
6. Abre el navegador web y navega la dirección `http://localhost:xxxx` para acceder a la aplicación.

## Notas

Se puede iniciar sesión con el usuario:

- Email: usuario3@example.com
- Contraseña: contraseña3

A la hora de buscar espacios, la mayoría de ellos están localizados en Madrid y Gipuzkoa, por lo que se recomienda alternar entre ambas provincias. En concreto, los espacios deportivos > Pistas de pádel en Gipuzkoa cuenta con bastantes datos.



**Proyecto creado por:**
    - Alejandro París Garcia y
    - Mikel Ballesteros Rodriguez 
