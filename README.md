# AgendaDeContactos
Este README proporciona instrucciones detalladas sobre cómo utilizar y probar la API desarrollada, así como una descripción general de sus características y funcionalidades.

Descripción General:
La API proporciona un sistema de gestión de contactos que permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre una lista de contactos almacenados en una base de datos.

Requisitos previos:
Antes de comenzar a utilizar la API, asegúrese de tener instalado lo siguiente:

Java JDK 17 o superior
Maven
Git
IDE (Eclipse, IntelliJ IDEA, etc.)
Postman (opcional, para probar las solicitudes HTTP)

Pasos para ejecutar la aplicación

1. Clone el repositorio:

git clone https://github.com/MonicaMRojas/AgendaDeContactos.git

2. Importe el proyecto en su IDE favorito.

3. No hace falta configurar la base de datos porque viene establecida en el application.properties para trabajar con BBDD embebida. Hay que tener en cuenta que se ha establecido el server.port como el 8081.

4. Compile y ejecute la aplicación desde su IDE o utilizando Maven:

mvn spring-boot:run / mvn clean instal

5. Una vez que la aplicación esté en funcionamiento, puede probar las diferentes API endpoints utilizando Postman u otra herramienta similar.

API Endpoints:

1. Crear un nuevo contacto
   
URL: /agenda/contactos/{id}

Método HTTP: POST

Descripción: Crea un nuevo contacto con la información proporcionada.

Parámetros de ruta:

- id: ID del contacto.
  
Cuerpo de la solicitud: Objeto JSON con los detalles del contacto.

Códigos de estado HTTP de respuesta:

201 Created: El contacto se creó correctamente.

404 Not Found: No se pudo crear el contacto.


2. Obtener todos los contactos
   
URL: /agenda/contactos/{parametro}/{orderBy}

Método HTTP: GET

Descripción: Devuelve una lista paginada de todos los contactos, ordenados según el parámetro especificado.

Parámetros de ruta:

- parametro: Campo por el que se ordenarán los contactos.
  
- orderBy: Dirección de ordenamiento (ASC o DESC).
  
Códigos de estado HTTP de respuesta:

200 OK: La solicitud se completó con éxito.

400 Bad Request: No se encontraron contactos.

206 Partial Content: Se devolvieron algunos contactos paginados.


3. Actualizar un contacto existente
   
URL: /agenda/contactos/{id}

Método HTTP: PUT

Descripción: Actualiza la información de un contacto existente.

Parámetros de ruta:

- id: ID del contacto a actualizar.
- 
Cuerpo de la solicitud: Objeto JSON con los nuevos detalles del contacto.

Códigos de estado HTTP de respuesta:

200 OK: El contacto se actualizó correctamente.

404 Not Found: No se encontró el contacto.



4. Eliminar un contacto
   
URL: /agenda/contactos/{id}

Método HTTP: DELETE

Descripción: Elimina un contacto existente por su ID.

Parámetros de ruta:

- id: ID del contacto a eliminar.
  
Códigos de estado HTTP de respuesta:

200 OK: El contacto se eliminó correctamente.

404 Not Found: No se encontró el contacto.

500 Internal Server Error: Error interno del servidor.



Ejemplo de uso:

Aquí hay un ejemplo de cómo utilizar la API utilizando Postman:

Crear un nuevo contacto:
1. Seleccionaremos el tipo POST.
2. En la URL pondremos http://localhost:8081/agenda/contactos/0
3. Seleccionamos Body.
4. Seleccionamos raw.
5. Nos aseguramos que esté establecido JSON.
6. En el cuerpo pegamos:
{
    "nombre": "Juan",
    "apellidos": "Pérez",
    "email": "juan@example.com"
}
7. Debe devolver algo asi y un 201 Created:
{
    "id": 1,
    "nombre": "Juan",
    "apellidos": "Pérez",
    "email": "juan@example.com"
}

Obtener todos los contactos ordenados ascendentemente por nombre:
1. Seleccionaremos el tipo GET.
2. En la URL pondremos http://localhost:8081/agenda/contactos/nombre/ASC
3. Debe mostrar algo asi y obtener un 206 Partial Content:
{
    "contactos": [
        {
            "id": 1,
            "nombre": "Juan",
            "apellidos": "Pérez",
            "email": "juan@example.com"
        }
    ],
    "totalElementos": 1,
    "paginasTotales": 1,
    "elementosPagina": 5,
    "paginaActual": 1
}

Actualizar un contacto existente:
1. Seleccionaremos el tipo PUT.
2. En la URL pondremos http://localhost:8081/agenda/contactos/1
3. Seleccionamos Body.
4. Seleccionamos raw.
5. Nos aseguramos que esté establecido JSON.
6. En el cuerpo pegamos (Se cambia nombre y email):
{
    "id": 1,
    "nombre": "Luis",
    "apellidos": "Pérez",
    "email": "luis@example.com"
}
7. Debe devolver y un 200 OK:
{
    "id": 1,
    "nombre": "Luis",
    "apellidos": "Pérez",
    "email": "luis@example.com"
}
8. Si queremos comprobarlo podemos utilizar el Get anterior para ver si ha cambiado realmente o no:
{
    "contactos": [
        {
            "id": 1,
            "nombre": "Luis",
            "apellidos": "Pérez",
            "email": "luis@example.com"
        }
    ],
    "totalElementos": 1,
    "paginasTotales": 1,
    "elementosPagina": 5,
    "paginaActual": 1
}


Eliminar un contacto existente:
1. Seleccionaremos el tipo DELETE.
2. En la URL pondremos http://localhost:8081/agenda/contactos/1
3. Debe devolver vacio y un 200 OK.


