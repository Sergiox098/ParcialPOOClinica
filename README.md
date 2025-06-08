# Gestor-de-Tiendas
Aplicación para poder administrar una tienda.


# Authentication, Realtime Database e interfaces de usuario.

Toda aplicación requiere certificar las credenciales de usuario, en otras palabras, poder registrar una cuenta en donde se guardarán los datos e ingresar a la misma, así que requieres una base de datos en dónde guardar la información de la cuenta y que el usuario pueda acceder en todo momento, pero se deberá crear una lógica muy extensa para que sea seguro, pero Firebase nos ofrece la opción adecuada para esta tarea: **Authentication**.

## ¿Qué es Authentication?

Firebase  Authentication es un servicio de Firebase, la cual está orientada a la gestuón y administración de cuentas de usuarios, de una manera segura y con métodos (Funciones) centradas en hacerlo lo más sencillo posible para su implementación.

¿Qué ventajas tiene?

- Posee un sistema de autenticación seguro y fácil de usar.
- Presente en una gran cantidad de lenguajes.
- Varias formas de acceso, con correo y contraseña, teléfono, cuenta de Google, cuenta de Facebook, entre otras.
- Gratuita.
- Tanto para proyectos grandes como pequeños.
- Maneja una gran cantidad de datos.

## Requerimientos necesarios.

El tutorial a continuación es para Android Studio usando Java. Para comenzar a usar Authentication sigue estos pasos:

- Crea un proyecto en Firebase. https://console.firebase.google.com
- Conectar tu aplicación con Firebase. Para conectarla ve a la consola de Firebase. Selecciona tu proyecto. Al inicio te saldrá esta imagen.
  
  ![image](https://github.com/user-attachments/assets/c4e3bf8c-ed26-4a24-a79b-90ad897486cc)

  Selecciona Android, añade el paquete y nombre de tu aplicación. También se puede de una manera más rápida desde el asistente de Firebase en Android Studio. Para algunos lenguajes esta opción puede ser opcional.
- Añade las dependencias a tu archivo gradle. Gradle es un administrador de dependencias (Librerias) que facilita bastante el proceso. Las dependencias necesarias son:
  
 > A nivel de proyecto: 'classpath 'com.google.gms:google-services:4.4.2
  
  > A nivel de App: 'apply plugin: 'com.google.gms.google-services'
  
  > A nivel de App: 'implementation 'com.google.firebase:firebase-auth:23.2.1'
  
  ![image](https://github.com/user-attachments/assets/1d40c76d-3797-4dbe-8a79-022c8b04034c)

  - Con esto ya podrás usar Authentication.


## Explicación Clase LoginViewModel.

La clase LoginViewModel es la clase en dónde se implemeta la API de Authentication y los métodos de "Iniciar Sesión de una aplicación, allí se validarán los datos ingresados y posteriormente los comparará con los que están en la base de datos. Vamos a detallarla.




## Implementación en Android Studio (Java)

En este caso de uso Authentication es util para manejar las cuentas de los usuarios que desean administar la tienda, asi qué, partiendo de 3 Fragments (Ventanas), uno de Login y otro de Register se recibirán los datos.

![image](https://github.com/user-attachments/assets/c4de6dfc-3438-4e84-a9d9-f304d3f3b0ab)
![image](https://github.com/user-attachments/assets/86cbbb02-86fb-4d83-9959-c80c754d2b9f)
![image](https://github.com/user-attachments/assets/a69aaaf8-a0e4-43a9-a744-9a58b1bad2bc)

En los campos de EditText, las cajas de texto, el usuario digita sus credenciales, y al oprimir el botón de Iniciar Sesión o Registrar, los datos son obtenidos.







