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

- Selecciona el método de acceso. Para este ejemplo Correo Electrónico/Contraseña

![image](https://github.com/user-attachments/assets/5281dbd9-6046-4b20-8938-cc27666a54c8)
  
- Añade las dependencias a tu archivo gradle. Gradle es un administrador de dependencias (Librerias) que facilita bastante el proceso. Las dependencias necesarias son:
  
 > A nivel de proyecto: 'classpath 'com.google.gms:google-services:4.4.2
  
  > A nivel de App: 'apply plugin: 'com.google.gms.google-services'
  
  > A nivel de App: 'implementation 'com.google.firebase:firebase-auth:23.2.1'
  
  ![image](https://github.com/user-attachments/assets/1d40c76d-3797-4dbe-8a79-022c8b04034c)

  - Con esto ya podrás usar Authentication.


## Explicación Clase LoginViewModel.

La clase LoginViewModel es la clase en dónde se implemeta la API de Authentication y los métodos de "Iniciar Sesión de una aplicación, allí se validarán los datos ingresados y posteriormente los comparará con los que están en la base de datos. Vamos a detallarla.

- Creacioón de un Fragment. Debes crear un fragment en donde vas a colocar el código y la interfaz de usuario.

![image](https://github.com/user-attachments/assets/acbf4e33-a6f1-4b2b-aa11-3e3918c11aeb)

- Importar librerías:

  ![image](https://github.com/user-attachments/assets/94e1ce0c-1405-4a54-b982-28476572184f)

  (Patterns es para validación de email, FirebaseAuth es para Authentication, LiveData y Mutable LiveData son variables que pueden ser observadas por otros objetos y actuar según sus cambios, es como una varible dentro de un ciclo.)

- Declaración de variables.

![image](https://github.com/user-attachments/assets/1d042c68-5c55-44e4-baf9-0acb8206f64d)

Se declaran las variables de tipo MutableLiveData, LoginSucces muestra si el ingreso fue correcto, Errormsg reporta el error que sucedió, Isloading dice sí el proceso está cargando. Son privadas ya que solo pueden modificarse con métodos de la clase.

![image](https://github.com/user-attachments/assets/33146499-dfef-484c-a842-94fa09067f02)

Variable de FirebaseAuth, permite acceder a los métodos de Authentication.

![image](https://github.com/user-attachments/assets/2edc231d-ac0e-412e-9c93-8796a6cc92f1)

Se crean unos getters Livedata, para mostrar el contenido de los MutableLiveData.

- Validación de datos.

  ![image](https://github.com/user-attachments/assets/a141ab17-39cc-4154-b714-7741e0cddaaf)

Con estos métodos se verifica que las credenciales cumples con los requisitos, Patterns define como debe ser un Email y que la contraseña debe tener 8 o más carácteres.

- Login.

![image](https://github.com/user-attachments/assets/f4d4bdeb-5089-4bf2-bcbe-1148fef38153)

Se comprueban que los datos proporcionados son adecuados con la sintaxis, sí no lo son retorna un mensaje de error.

![image](https://github.com/user-attachments/assets/5d6dca14-e900-48c7-9e34-fdc6fce92734)

Se envía el mensaje que está cargando y comienza con el proceso de Firebase.

A través del método .signInWithEmailAndPassword(email, password) se validan las credenciales que concuerden con alguna de las cuentas en las bases de datos y retorna un task. Cuando se completa la tarea se detiene la carga, y sí fue exitosa se cambia la variable LoginSuccess, y sí no, retorna un mensaje de error.

## Explicación Clase RegisterAuthMV.

La clase RegisterAuthMV, es muy similar, ya que comparten la misma idea que login: variables LiveData que se comunican con los Fragments y la Validaciones de datos(Email válido, Contraseña, Confirmar contraseña), solo que cambian en el método de Authentication.

![image](https://github.com/user-attachments/assets/bc8a2dac-0e68-4e86-a085-82fe01107bd7)

Este método analiza toda la base de datos y sí no hay ningún correo igual se sube exitosamente. Es muy fácil trabajar con Authentication.

En este caso como un requerimiento adicional para el proyecto, también se implementa Realtime database para guardar datos de la cuenta. El proceso es:
- Sí la tarea de registrar la cuenta fué exitosa, se obtiene el uid de la cuenta.
- Se crea un HashMap. (Un diccionario clave, valor. Realtime Database trabaja con este formato)
- El Hashmap almacenará nombre de usuario, email y teléfono.
- Se sube el mapa a la base de datos en la key (direccion, nodo) respectiva al uid de la cuenta.
- Sí la tarea fue exitosa se envía un mensaje de éxito, sí no, un error.
  

## Recolección de Datos.

En este caso de uso Authentication es util para manejar las cuentas de los usuarios que desean administar la tienda, asi qué, partiendo de 3 Fragments (Ventanas), uno de Login y otro de Register se recibirán los datos.

![image](https://github.com/user-attachments/assets/c4de6dfc-3438-4e84-a9d9-f304d3f3b0ab)
![image](https://github.com/user-attachments/assets/86cbbb02-86fb-4d83-9959-c80c754d2b9f)
![image](https://github.com/user-attachments/assets/a69aaaf8-a0e4-43a9-a744-9a58b1bad2bc)

En los campos de EditText, las cajas de texto, el usuario digita sus credenciales, y al oprimir el botón de Iniciar Sesión o Registrar, los datos son obtenidos.

- Importación de liberias. (La mayoría vienen por defecto)

![image](https://github.com/user-attachments/assets/841ee634-ad96-41d7-8869-391655c62c75)

- Crear un Binding. Facilita la interacción entre las vistas, los xml, y los fragments, es el comunicador entre la interfaz y la lógica. En este caso es el responsable de obtener los datos de las cajas de texto.

![image](https://github.com/user-attachments/assets/8c9e1963-f363-4ab4-865e-768794789477)

- Crear la vista, aquí se infla el Binding, en pocas palabras, enlazar el xml y la lógica.

![image](https://github.com/user-attachments/assets/be0a0e53-8526-4c75-ac38-990e9d9ace84)

- Navegación con botones de la vista.

![image](https://github.com/user-attachments/assets/612cd923-1513-4014-b7a1-7ec653845916)

- IsLoading. Este método dependiendo de lo que retorne la clase anterior, mostrará la barra de progreso o la ocultará.

![image](https://github.com/user-attachments/assets/07ac2f1f-352b-4d94-888b-ff020d364f2b)

- ErrorMessage. Muestra el mensaje de error de la clase en un toast.

![image](https://github.com/user-attachments/assets/88c60e32-c5f7-4df9-afb6-e033b39b71ec)

- Register Success. Sí el registro fue exitoso se crea un toast notificandolo y se envía a la otra activity.

![image](https://github.com/user-attachments/assets/f50f3ab6-daa0-4b2a-821d-cf94bb729c5d)

- Register. Cuando el botón de register se presiona, el binding obtiene los datos en ese momento de las cajas de texto y los guarda en variables que posteriormente enviará al método register de la clase RegisterAuthMV y se realizará la validación.

![image](https://github.com/user-attachments/assets/2ce292b1-e29d-4ac0-8639-bc523c236ce0)

Así finaliza el register, login es igual, pero solo cambia el final ya que requiere menos datos.

## Ejemplo de uso

![Imagen de WhatsApp 2025-06-07 a las 23 50 22_74dde182](https://github.com/user-attachments/assets/3e02c602-e3f6-4f39-bda1-e3d5c5038bfb)

SplashScreen.

![Imagen de WhatsApp 2025-06-08 a las 01 37 21_ff484225](https://github.com/user-attachments/assets/a7311b8e-726c-4e87-9b21-8f63a8982274)

Register.

![Imagen de WhatsApp 2025-06-08 a las 01 37 21_74052149](https://github.com/user-attachments/assets/d0f69801-e9eb-4d8f-8e8d-22b32432d60a)

Pantalla de Carga

![Imagen de WhatsApp 2025-06-08 a las 01 37 21_46190ef5](https://github.com/user-attachments/assets/7863d2cf-9c26-4ece-93b8-506ccbd40712)

Registro Exitoso.

![image](https://github.com/user-attachments/assets/02f99098-3027-418e-bcf1-2ff408f0be9c)


Authentication.

![image](https://github.com/user-attachments/assets/a9d26a39-a5b4-4b67-9f9a-59d2edfc905a)


Realtime Database.
