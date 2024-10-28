# Test Application

## Descripción

Este proyecto es una aplicación de ejemplo que utiliza Java, Spring Boot y Maven. La aplicación se encarga de gestionar tarifas de productos, permitiendo
recuperar tarifas basadas en diferentes criterios.

## Tecnologías

- **Java**
- **Spring Boot**
- **Maven**
- **SQL**
- **H2 Database**

## Estructura del Proyecto

El proyecto está estructurado usando arquitectura hexagonal y DDD. Aunque en este caso la distinción entre dominios o hexágonos no es tan clara, en un proyecto
más grande la intención es crear una separación vertical entre los diferentes hexágonos. El proyecto se divide en las siguientes capas, para cada una de las
cuales se ha creado un paquete:

- `domain`: Contiene las clases de dominio del proyecto. En este caso, las clases de dominio son las tarifas. Provee la relación entre las diferentes entidades
  y agregados de nuestro dominio. También a esta capa pertenecen los puertos de nuestro dominio que definen las operaciones sobre el negocio independientemente
  de la tecnología o infraestructura utilizada.
- `application`: Contiene las clases de aplicación y lógica de negocio. Estas exponen los servicios que se pueden consumir desde el exterior y que coordinan los
  flujos de nuestra aplicación. En un modelo de arquitectura hexagonal se suele encontrar que las clases que forman la capa de aplicación implementan los
  puertos primarios o inbound especificados en el dominio habilitando un aislamiento todavía mayor entre la capa de infraestructura la de aplicación. En este
  caso
  por la sencillez del proyecto no se ha implementado esta separación.
- `infrastructure`: Contiene las clases de infraestructura, como la implementación de los repositorios y que permiten que el dominio permanezca agnóstico de
  la tecnología utilizada.

Además de esta separación de paquetes es común encontrar en proyectos de mayor tamaño una recopilación de servicios y utilidades comunes a todos los hexágonos,
comunmente llamado `shared kernel` o `support` entre otros.

## Testing

Se han incluido test unitarios para la capa de aplicación e infraestructura en los adaptadores primarios, así como test de integración del flujo completo.

## Próximos pasos y mejoras

He tomado la decisión de ceñirme al máximo a los requisitos del proyecto sin incluir comprobaciones adicionales o mejoras que no se solicitan. Sin embargo,
podemos considerar las siguientes mejoras:

- **Validación de datos en los controllers**: He dejado la comprobación de los datos de entrada al por defecto de Spring Boot. Para hacer que los mensajes de
  error fuesen más ricos, y controlar las respuestas en caso de error se podría realizar una validación de datos e implementar una respuesta personalizada de
  mensaje de error.
- **Documentación de la API**: Se podría incluir una documentación de la API utilizando Swagger o similar.
- **Más tests unitarios de la capa de aplicación**: Se podrían incluir más tests unitarios para la capa de aplicación. Sólo me he limitado a los requeridos por
  el enunciado del proyecto. En un caso real de una aplicación cuyo destino es producción es necesario probar casos de error y casos límite.

## Git

En este caso y dada la sencillez del proyecto he optado por no utilizar ramas. En un proyecto real se debería utilizar ramas para separar las diferentes tareas
o casos de uso. Una tecnica bastante comun es la conocida como [GifFlow](https://mahdiaaliyya.medium.com/understanding-git-and-git-flow-80b5cdafdcb)

## Requisitos

- **Java 21** o superior

