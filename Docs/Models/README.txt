En esta carpeta se almacena la información relativa al modelo de la aplicación de cargadores de coches eléctricos.

## Descripción del contenido

Esta carpeta contiene (originalmente) estos ficheros:

- arquitectura.puml: descripción de la arquitectura inicial de la aplicación, utilizando el lenguaje PlantUML
- arquitectura.png : diagrama de arquitectura generado a partir del fichero arquitectura.puml
- dominio.puml : descripción del dominio inicial de la aplicación, utilizando el lenguaje PlantUML
- dominio.png : diagrama del dominio generado a partir del fichero arquitectura.puml
- dominioCompletoServicio.png : diagrama del dominio completo del servicio OpenChargeMap. Creado a partir de la documentación oficial: https://openchargemap.org/site/develop/api#/schemas/POI
- ocm-core-ref-data.json : datos de referencia del servicio OpenChargeMap. Contiene todos los valores conocidos de todos los campos proporcionados por el servicio. Se ha obtenido a partir de este endpoint del servicio: https://openchargemap.org/site/develop/api#/operations/get-referencedata
- ocm-poi-ref-response.json : ejemplo de respuesta del servicio de OpenChargeMap. Contiene 2 estaciones de carga. Esta es la respuesta de ejemplo que se muestra en https://openchargemap.org/site/develop/api#/operations/get-poi
- README.txt : este fichero

## Cómo compilar ficheros PlantUML

La manera más sencilla de generar una imagen a partir de la descripción PlantUML es mediante el editor online disponible en http://www.plantuml.com/plantuml/uml/

Basta con copiar el código completo dentro del cuadro de texto superior. La imagen se generará automáticamente al cabo de unos segundos. Se puede forzar la generación de la imagen mediante el botón Submit.

Para descargar la imagen en formato png hay que clickar en el botón PNG.