# ConversorDivisas
Conversor de Monedas en Java
📝 Descripción del Proyecto
Este es un Conversor de Monedas desarrollado en Java , que consume una API externa para obtener las tasas de cambio actualizadas y permite al usuario convertir entre diferentes monedas.

La aplicación está construida bajo una arquitectura limpia y modular, siguiendo buenas prácticas de programación orientada a objetos (POO) y principios de diseño como separación de responsabilidades y reutilización de código .

El proyecto incluye funcionalidades como:

Consumo de una API RESTful (exchangerate.host u otra API pública)
Manejo de respuestas JSON mediante la biblioteca Gson
Manejo de errores personalizados
Registro de conversiones en archivos locales
Interfaz de consola interactiva (opcional)
⚙️ Tecnologías Utilizadas
Java 11+
HttpClient (API nativa de Java para solicitudes HTTP)
Gson (Google Gson) – Para serialización/deserialización de JSON
java.io – Para manejo básico de archivos
POO (Programación Orientada a Objetos) – Principios de encapsulamiento, herencia y polimorfismo
Patrones de Diseño – Separación por capas, inyección simple de dependencias

🧪 Funcionalidades Principales
Obtener tasas de cambio desde una API externa
Parsear respuestas JSON a objetos Java con Gson
Realizar conversiones entre distintas monedas
Manejar errores de conexión y formato de datos
Registrar historial de conversiones en un archivo local (JSON o TXT)
🧱 Metodología de Desarrollo
Este proyecto fue desarrollado utilizando una metodología orientada a objetos , dividiendo el problema en componentes reutilizables y escalables.

✅ Enfoque Adoptado:
División por capas : Se separaron las responsabilidades en paquetes según su función.
Principios SOLID básicos :
Single Responsibility Principle : Cada clase tiene una sola responsabilidad clara.
Open/Closed Principle : Las clases están abiertas para extensión, cerradas para modificación.
Reutilización de código : Métodos auxiliares compartidos evitan duplicados.
Separación de lógica y presentación : La interfaz (consola) no afecta la lógica interna.


🎯 Patrones de Diseño Aplicados
Aunque este es un proyecto sencillo, se han aplicado conceptos clave de diseño orientado a objetos:

DTO (Data Transfer Object)
Moneda
,
TasaCambio
representan modelos simples para transferir datos
Servicio / Service Layer
ConversorService
encapsula la lógica de negocio
Factory Method (implícito)
JsonParser
actúa como fábrica de objetos Java a partir de JSON
Constructor Injection
Se inyectan dependencias manualmente usando constructores
Excepciones Personalizadas
Se lanzan mensajes claros cuando algo falla
