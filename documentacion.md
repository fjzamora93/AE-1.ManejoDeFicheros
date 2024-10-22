# AE-1. Manejo de Ficheros

Esta práctica consiste en la implementación de un programa Java 
para la gestión del almacén de coches en un concesionario. 

### Modelo "Coche"
Los coches tendrán los siguientes atributos:
- id
- matricula
- marca
- modelo 
- color.

**No se permite duplicar el id ni la matricula.**


### Menú principal
El usuario interactuará con el programa a través del siguiente menú, 
que servirá como interfaz.

-Añadir nuevo coche
-Borrar coche por id
-Consulta coche por id
-Listado de coches
-Convertir a CSV
-Terminar el programa

Las opciones del menú 1 a 5 trabajarán sobre la colección de tipo ArrayList 
para añadir, borrar, consultar o listar, y no sobre el fichero coches.dat.


### Antes de la ejecución
Nada más comenzar la ejecución del programa se debe verificar 
si existe el fichero coches.dat (fichero que contendrá objetos Coche).

Si existe, debes leerlo para llenar una colección de tipo ArrayList 
con todos los objetos Coche existentes en el fichero. 
Si no existe el archivo coches.dat, no tendrás nada que hacer por el momento, pero sí debes dejar la colección ArrayList disponible, aunque esté vacía.

Cuando el usuario decida terminar la ejecución del programa pulsando la opción 5,
el programa deberá crear el fichero coches.dat, 
sobrescribiendo el anterior si existiera. 
Se escribirán en el fichero tantos coches como elementos tenga la colección ArrayList que has creado.


### Exportación a CSV

Se añadirá una opción al menú que será “Exportar coches a archivo CSV”, 
que creará un fichero (coches.csv) donde guardará la información de los coches 
con el formato de un archivo csv, es decir, valores separados por el carácter “;”.

Comprobar que dicho fichero se puede abrir con un programa como Excel 
o alguna herramienta en online.



### Documentación

-Para la entrega, se subirá un documento PDF con
(resultados, capturas de pantalla, ficheros, fotos, etc.).

-Que incluya, al menos, la metodología de trabajo del grupo,
las capturas de los resultados obtenidos con los comentarios pertinentes, 
y la explicación de los puntos clave de la actividad realizada. 

-No cumplir con este punto puede llevar a suspender la actividad 
o a reducir considerablemente la nota final.

-Además, para toda la actividad se valorará la claridad de código, 
la modularidad y la eficiencia de los algoritmos empleados.

-Para la actividad se recomienda que todos los integrantes
aporten una posible solución y luego elijan cual será la solución final 
mediante consenso.

-Dejar el código subido a Github.

-Se puede subir el código fuente también comprimido a la plataforma 
en su lugar si así se prefiere, pero en este caso, el fichero PDF 
y el código comprimido (fichero .zip o .7z) deben de ir por separado.