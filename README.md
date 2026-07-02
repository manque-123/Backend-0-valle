# Valle del Sol - Backend

Backend desarrollado con Java Spring Boot para la gestión de emergencias del sistema Valle del Sol.

## Objetivo

Permitir el registro, consulta, actualización y eliminación de reportes de emergencia enviados desde la aplicación móvil.

## Tecnologías

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Render
- Docker

## Arquitectura

El backend utiliza una estructura por capas:

- Controller: recibe las solicitudes HTTP.
- Model: representa la entidad Emergencia.
- Repository: gestiona el acceso a la base de datos.

## Endpoint principal

URL desplegada:

https://backend-0-valle.onrender.com/api/emergencias

## Operaciones disponibles

GET /api/emergencias  
Lista todos los reportes.

POST /api/emergencias  
Crea un nuevo reporte.

PUT /api/emergencias/{id}  
Actualiza un reporte existente.

DELETE /api/emergencias/{id}  
Elimina un reporte.

## Campos principales del reporte

- id
- tipo
- descripcion
- ubicacion
- estado
- latitud
- longitud
- gravedad
- ciudadano
- evidenciaFoto
- derivadoA
- responsable
- fechaDerivacion
- fechaActualizacion
- fecha

## Despliegue

El backend está desplegado en Render desde la rama main.

## Comandos útiles

Compilar el proyecto:

mvn clean package -DskipTests

Ejecutar localmente:

mvn spring-boot:run

## Estado del proyecto

- CRUD completo funcionando.
- Backend conectado a base de datos.
- Despliegue activo en Render.
- Integración probada con frontend Ionic/Angular.
