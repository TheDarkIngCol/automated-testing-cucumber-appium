# Automatización BDD Web y Mobile

Proyecto de pruebas automatizadas desarrollado con **Java + Cucumber**, implementando pruebas para **Web** con **Selenium** y para **Mobile** con **Appium**.

Está estructurado bajo el patrón **Page Object Model (POM)**, con una separación clara entre plataformas para facilitar mantenimiento y escalabilidad.

---

## Tecnologías y Herramientas

- Java 21+
- Cucumber (BDD)
- Selenium WebDriver (Automatización Web)
- Appium (Automatización Mobile)
- Maven (Gestión de dependencias y ejecución)
- JUnit / TestNG (Framework de ejecución de tests)
- Page Object Model (Diseño y organización de código)


## Cómo Ejecutar las Pruebas
- appium --base-path /wd/hub --allow-cors

### Pruebas Web

1. Configurar el navegador en el archivo de configuración.
2. Ejecutar con Maven:


```mvn clean test```
