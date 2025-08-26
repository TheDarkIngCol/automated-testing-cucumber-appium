# 🚀 Automatización BDD Web y Mobile

Proyecto de pruebas automatizadas desarrollado con **Java + Cucumber**, implementando pruebas para **Web con Selenium** y **Mobile con Appium**.  
El diseño sigue el patrón **Page Object Model (POM)**, con separación clara de plataformas para facilitar mantenimiento y escalabilidad. 
---
Desarrollado por: **Jorge Ivan Torres Florez | QA Automation Engineer | SDET**

---

## 🧰 Tecnologías y Herramientas

- **Lenguaje:** Java 21+
- **BDD:** Cucumber
- **Automatización Web:** Selenium WebDriver
- **Automatización Mobile:** Appium
- **Gestión de dependencias:** Maven
- **Framework de ejecución:** JUnit / TestNG
- **Diseño de código:** Page Object Model (POM)
- **Ejecución remota:** BrowserStack

---

## ⚙️ Configuración Inicial
### 🔧 Appium Local
Antes de ejecutar pruebas mobile, asegúrate de que Appium esté corriendo:

    appium --base-path /wd/hub --allow-cors
    emulator -avd Medium_Phone_API_36.0

---

## ☁️ BrowserStack
Configura tus credenciales en variables de entorno:

- **BROWSERSTACK_USER:** <TU_USUARIO>
- **BROWSERSTACK_KEY:** <TU_KEY>

---

## ▶️ Cómo Ejecutar las Pruebas
### 🌐 Pruebas Web Local
```mvn clean test -DsuiteXmlFile=testng-web.xml```

### 🌐 Pruebas Web en BrowserStack
```mvn clean test -DsuiteXmlFile=testng-web.xml -Dbrowserstack=true```

### 🤖 + 🍎 Pruebas Mobile Local (Android + iOS)
```mvn clean test -DsuiteXmlFile=testng-mobile.xml```

### 🤖 + 🍎 Pruebas Mobile en BrowserStack (Android + iOS)
```mvn clean test -DsuiteXmlFile=testng-mobile.xml -Dbrowserstack=true```

## 📚 Reportes Generados
### WEB
- **target/cucumber-html-reports/web/web-report.html**

### Mobile
- **target/cucumber-html-reports/mobile/android/android-report.html**
- **target/cucumber-html-reports/mobile/iOS/iOS-report.html**