## 📅 Medical Appointment Booking System

### Functional requirements

1. 👤 Patients and doctors can create an account with their basic information
2. Users can log in with their email and password
3. Patients can book, modify, or cancel an appointment
4. Patients can choose the doctor for their care
5. Patients can select the time and date proposed by the doctor according to their availability
6. Doctors can set up their availability for consultations
7. Doctors can access the patient’s history
8. Patients can access their history of previous appointments
8. The doctor can add details of the consultation to the patient's history
9. Patients can search for doctors by specialty or name
10. Patients can make payments online
11. Patients will receive a summary of their reserved appointment via email
12. Doctors will receive a notification when a patient books an appointment with them
13. Administrators can add and assign roles to new users

<!--  Spanish version
1. Los pacientes y los medicos pueden crear una cuenta con su informacion basica
2. Los usuarios podran iniciar sesion con su correo y contraseña
3. Los pacientes pueden reservar, modificar  o cancelar una cita
4. Los pacientes pueden elegir el medico para su atencion 
5. Los pacientes pueden elegir el horario y fecha propuesto por el medico de acuerdo a su disponibilidad
6. Los medicos pueden configurar la disponibilidad para sus consultas
7. Los medicos pueden acceder al historial del paciente
8. El medico puede agregar detalles de la consulta al historial del paciente
8. Los pacientes pueden acceder a su historial de citas anteriores
9. Los pacientes pueden buscar medicos por especialidad o nombre
10. Los pacientes pueden realizar pagos en linea
11. Los pacientes recibiran un resumen de su cita reservada por correo electronico
12. Los médicos recibirán una notificación cuando un paciente reserve una cita con ellos
Los administradores pueden añadir y asignar roles de los nuevos usuarios
-->

### Non-functional requirements

1. The interface should be simple and intuitive for users
2. The system should implement a two-factor authentication mechanism
3. The system should be compatible with mobile and desktop devices
4. The system should respond to most user requests in less than 2 seconds
5. The system should be available 24/7
6. The system should support multiple requests without affecting its performance
7. The system should support more users as it grows

<!--  Spanish version
1. La interfaz debe ser simple e intuitiva para usuarios
2. El sistema debe implementar un mecanismo de autentificacion de dos factores
3. El sistema debe ser compatible con dispositivos móviles y de escritorio
4. El sistema debe responder a la mayoria de solicitudes del usuario en menos de 2 segundos
5. El sistema debe estar disponible 24/7
6. El sistema debe soportar multiples solicitudes sin afectar su desempeño
7. El sistema debe soportar a mas usuarios a medida que va creciendo
-->

<!--
# Requerimientos Funcionales
## Gestión de Usuarios

- **Registrar usuarios**: Los administradores pueden añadir nuevos usuarios y asignarles roles como administrador, médico, paciente o auxiliar.
- **Autenticación**: Todos los usuarios pueden iniciar sesión con sus credenciales.
- **Asignar roles**: Los administradores pueden cambiar o asignar roles a los usuarios.

## Gestión de Pacientes

- **Registrar pacientes**: Médicos y personal administrativo pueden añadir nuevos pacientes.
- **Actualizar información**: Los médicos pueden modificar la información personal y médica de los pacientes.
- **Consultar historial médico**: Los médicos tienen acceso al historial médico de los pacientes.

## Gestión de Citas Médicas

- **Programar citas**: Pacientes y médicos pueden agendar nuevas citas.
- **Modificar citas**: Se pueden cambiar citas ya programadas.
- **Cancelar citas**: Las citas pueden ser canceladas por pacientes y médicos.
- **Notificaciones**: El sistema enviará recordatorios de citas a pacientes y médicos.

## Gestión de Farmacia

- **Registrar fármacos**: El personal de farmacia puede añadir nuevos medicamentos.
- **Actualizar inventario**: Se puede actualizar el stock de medicamentos.
- **Emitir prescripciones**: Los médicos pueden crear y gestionar prescripciones electrónicas.

## Gestión de Especialidades y Horarios

- **Registrar especialidades**: Los administradores pueden añadir nuevas especialidades médicas.
- **Actualizar horarios**: Los médicos pueden modificar sus horarios de atención.

## Gestión de Almacén

- **Registrar suministros**: El personal de almacén puede añadir nuevos suministros médicos.
- **Actualizar inventario**: Se puede actualizar el stock de suministros.
- **Alertas de stock**: El sistema notificará cuando el stock de suministros esté bajo.

## Triaje

- **Evaluar pacientes**: El personal de triaje clasifica a los pacientes según la urgencia de su condición.
- **Registrar evaluaciones**: Las evaluaciones realizadas por el personal de triaje se registran en el sistema.

## Reportes y Estadísticas

- **Generar reportes**: Administradores y personal autorizado pueden crear reportes sobre citas, pacientes, inventarios, etc.
- **Consultar estadísticas**: Se pueden revisar estadísticas sobre el uso del sistema y otros indicadores clave.

Requisitos No funcionales:

  
## Identificación del requerimiento:  	RNF01 
Nombre del Requerimiento:  	Usabilidad 
Dependencias 	Ninguno  
Versión 	1.0 
Características:  	Facilidad de aprendizaje, Facilidad de recordación, Capacidad de error, Satisfacción del usuario y Accesibilidad 
Descripción del requerimiento:  	El sistema debe tener una interfaz de uso intuitiva y sencilla. 
	
Estado 	Pendiente (Verificación - Confirmación) 
Prioridad del requerimiento:      
Alta  
Comentarios  	Ninguno  
Trade-Off 	Facilidad de uso vs. Funcionalidad 
Pros de la facilidad de uso: 
•	El sistema es más fácil de aprender y usar. 
•	Es menos probable que los usuarios cometan errores. 
Contras de la facilidad de uso: 
•	El sistema puede tener menos funcionalidades. 
•	El sistema puede ser menos flexible.  



## Identificación del requerimiento:  	RNF02 
Nombre del Requerimiento:  	Seguridad  
Dependencias 	Ninguno  
Versión 	1.0 
Características:  	Autenticación, Cifrado de datos , Actualizaciones y parches y Respaldo y recuperación 
Descripción del requerimiento:  	El sistema garantizara a los usuarios una seguridad y privacidad en cuanto a la información que se procede en el sistema. Cumpliendo con las regulaciones de protección de datos. 
	
Estado 	Pendiente (Verificación - Confirmación) 
Prioridad del requerimiento:      
Alta  
Comentarios  	Ninguno  
Trade-Off 
 	Seguridad vs. Facilidad de uso: 
•	Pros de la seguridad: 
o	El sistema es más seguro. 
o	Los usuarios se sienten más seguros al usar el sistema. 
•	Contras de la seguridad: 
o	El sistema puede ser menos fácil de usar. 
o	Los usuarios pueden tener que recordar más contraseñas o realizar más pasos para acceder a la información. 
 


## Identificación del requerimiento:  	RNF03 
Nombre del Requerimiento:  	Eficiencia 
Dependencias 	Ninguno  
Versión 	1.0 
Características:  	Rendimiento, Uso de recursos, Optimización de código, Optimización de procesos 
Descripción del requerimiento:  	Busca asegurar que el sistema cumpla con ciertos estándares, garantizando una experiencia de usuario satisfactoria y una operación efectiva del sistema. 
	
Estado 	Pendiente (Verificación - Confirmación) 
Prioridad del requerimiento:      
Alta  
Comentarios  	Ninguno  
Trade-Off 
 	Rendimiento vs. Costo: 
•	Pros de un alto rendimiento: 
o	El sistema puede manejar un mayor volumen de usuarios y transacciones. 
o	Los usuarios experimentarán un mejor tiempo de respuesta. 
•	Contras de un alto rendimiento: 
o	El sistema puede ser más costoso de desarrollar y mantener. 
o	El sistema puede requerir más hardware y software.  



## Identificación del requerimiento:  	RNF04 
Nombre del Requerimiento:  	Desempeño 
Dependencias 	Ninguno  
Versión 	1.0 
Características:  	Velocidad, Tiempo de respuesta, Capacidad de carga, Fiabilidad 
Descripción del requerimiento:  	Garantizar el desempeño del sistema, de acuerdo con los diferentes usuarios. En este sentido la información almacenada o registros realizados podrán ser consultados y actualizados permanente y simultáneamente, sin que se afecte el tiempo de respuesta. 
	
Estado 	Pendiente (Verificación - Confirmación) 
Prioridad del requerimiento:      
Alta  
Comentarios  	Ninguno  
Trade-Off 
 	Confiabilidad vs. Costo: 
•	Pros de una alta confiabilidad: 
o	El sistema tendrá menos tiempo de inactividad. 
o	Los usuarios experimentarán menos interrupciones. 
•	Contras de una alta confiabilidad: 
o	El sistema puede ser más costoso de desarrollar y mantener. 
o	El sistema puede requerir más hardware y software.  

	


## Identificación del requerimiento:  	RNF05 
Nombre del Requerimiento:  	Confiabilidad continúa 
Dependencias 	Ninguno  
Versión 	1.0 
Características:  	Tolerancia a fallos, Resistencia a errores 
Descripción del requerimiento:  	La disponibilidad del sistema debe ser continua con un nivel de servicio para los usuarios de 7 días por 24 horas, garantizando un esquema adecuado que permita manejar las posibles fallas en cualquiera de sus componentes, contar con una contingencia, generación de alarmas. 
	
Estado 	Pendiente (Verificación - Confirmación) 
Prioridad del requerimiento:      
Alta  
Comentarios  	Ninguno  
Trade-Off 
 	Escalabilidad vs. Costo: 
•	Pros de una alta escalabilidad: 
o	El sistema podrá admitir más usuarios y transacciones. 
o	La empresa podrá crecer sin tener que invertir en nuevos sistemas. 
•	Contras de una alta escalabilidad: 
o	El sistema puede ser más costoso de desarrollar y mantener. 
o	El sistema puede requerir más hardware y software.  




## Identificación del requerimiento:  	RNF06 
Nombre del Requerimiento:  	Mantenimiento. 
Dependencias 	Ninguno  
Versión 	1.0 
Características:  	Modularidad, Comentarios en el código, Documentación, Versionado y control de cambios y pruebas 
Descripción del requerimiento:  	El sistema debe disponer de una documentación fácilmente actualizable que permita realizar operaciones de mantenimiento con el menor esfuerzo posible. 
	
Estado 	Pendiente (Verificación - Confirmación) 
Prioridad del requerimiento:      
Alta  
Comentarios  	Ninguno  
Trade-Off 
 	Tradeoff 1: Modularidad vs. Eficiencia: 
•	Pros de una alta modularidad: 
o	El sistema será más fácil de entender y mantener. 
o	Será más fácil extender el sistema en el futuro. 
•	Contras de una alta modularidad: 
o	El sistema puede ser menos eficiente. 
o	Puede haber más sobrecarga de rendimiento debido a la comunicación entre módulos. 
 


## Identificación del requerimiento:  	RNF07 
Nombre del Requerimiento:  	Rendimiento 
Dependencias 	Ninguno  
Versión 	1.0 
Características:  	Capacidad de procesamiento, Tiempo de respuesta, Carga de trabajo máxima, Tolerancia al estrés 
Descripción del requerimiento:  	El aplicativo debe tener tiempo de respuesta debe ser mínimo, tanto para las solicitudes de los usuarios como para las actualizaciones automáticas del sistema. 
	
Estado 	Pendiente (Verificación - Confirmación) 
Prioridad del requerimiento:      
Alta  
Comentarios  	Ninguno  
Trade-Off 
 	Capacidad de procesamiento vs. Costo: 
•	Pros de una alta capacidad de procesamiento: 
o	El sistema podrá manejar una mayor carga de trabajo. 
o	Los usuarios experimentarán un mejor rendimiento. 
•	Contras de una alta capacidad de procesamiento: 
o	El sistema puede ser más costoso de desarrollar y mantener. 
o	El sistema puede requerir más hardware y software.  





## Identificación del requerimiento:  	RNF08 
Nombre del Requerimiento:  	Soporte 
Dependencias 	Ninguno  
Versión 	1.0 
Características:  	Soporte multicanal, Capacitación y recursos de aprendizaje 
Descripción del requerimiento:  	La interfaz debe estar complementada con un buen sistema de ayuda (la administración puede recaer en personal con poca experiencia en el uso de aplicaciones informáticas). 
	
Estado 	Pendiente (Verificación - Confirmación) 
Prioridad del requerimiento:      
Alta  
Comentarios  	Ninguno  
Trade-Off 
 	Usabilidad vs. Funcionalidad: 
•	Pros de una alta usabilidad: 
o	El sistema será más fácil de usar para los usuarios. 
o	Los usuarios serán más productivos. 
•	Contras de una alta usabilidad: 
o	El sistema puede tener menos funcionalidades. 
o	El sistema puede ser menos flexible.  
-->