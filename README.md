# Control De Pacientes Hospitalarios

# Introducción:

Este proyecto de control de pacientes hospitalarios se centra en la gestión de información de pacientes a través de una interfaz de línea de comandos. El programa proporciona funcionalidades como registro de usuarios, inicio de sesión, inserción de datos de pacientes y operaciones de ordenamiento y búsqueda. Aquí te proporcionaré una explicación más detallada.

# Registro e Inicio de Sesión:

El programa inicia con un menú de login que ofrece dos opciones principales: registro e inicio de sesión. En la opción de registro, el usuario puede crear un nuevo nombre de usuario y contraseña, que se almacenan en un archivo CSV llamado "users.csv". Esto permite que diferentes usuarios tengan acceso al programa con credenciales únicas.
En la opción de inicio de sesión, el usuario ingresa su nombre de usuario y contraseña. Estas credenciales se comparan con los datos almacenados en "users.csv". Si hay coincidencia, el programa da la bienvenida al usuario y muestra el menú principal.

# Menú Principal:

Una vez que un usuario inicia sesión, se presenta un menú principal que ofrece varias opciones:

* Inserción de Datos de Pacientes:
Permite al usuario ingresar información detallada sobre un paciente, como nombre, dirección, número de teléfono, nacionalidad, género, número de seguro social (NSS), préstamos, incapacidad, estado de defunción y fecha de alta.

* Visualización de Todos los Datos:
Muestra todos los datos de pacientes almacenados en el archivo "data.csv". Cada paciente se muestra en una estructura tabular con todos sus detalles.

* Ordenamiento de Datos:
Permite al usuario seleccionar aleatoriamente un método de ordenamiento para ordenar los datos de pacientes. Se incluyen una variedad de algoritmos de ordenamiento, desde burbuja hasta quicksort.

* Búsqueda de Datos:

Similar al ordenamiento, el programa ofrece una opción para seleccionar aleatoriamente un método de búsqueda. Puedes buscar pacientes por apellido o NSS, utilizando métodos como búsqueda binaria y búsqueda secuencial.
- Salir:

Cierra el programa después de agradecer al usuario por su uso.
* Funciones Adicionales:

Se implementa un manejo básico de excepciones para lidiar con errores durante la lectura y escritura de archivos.
El programa evalúa si los datos están ordenados antes de realizar ciertas operaciones para garantizar resultados precisos.
Observaciones Finales:

Este proyecto proporciona una funcionalidad integral para gestionar datos de pacientes hospitalarios. El enfoque de selección aleatoria para algoritmos de ordenamiento y búsqueda agrega un elemento interesante al proyecto. Sin embargo, se recomendaría una mayor modularidad y comentarios para mejorar la comprensión del código. Además, es importante probar exhaustivamente el programa para garantizar su robustez y manejo adecuado de datos.
