# 🚀 Automatización BDD Web y Mobile

Proyecto de pruebas automatizadas desarrollado con **Java + Cucumber**, implementando pruebas para **Web con Selenium** y **Mobile con Appium**.  
El diseño sigue el patrón **Page Object Model (POM)**, con separación clara de plataformas para facilitar mantenimiento y escalabilidad. 
---
Desarrollado por: **Jorge Ivan Torres Florez | QA Automation Engineer |**

---

## 🧰 Tecnologías y Herramientas

- **Lenguaje:** Java 17+ (recomendado LTS)
- **BDD:** Cucumber
- **Automatización Web:** Selenium WebDriver
- **Automatización Mobile:** Appium
- **Gestión de dependencias:** Maven
- **Framework de ejecución:** JUnit / TestNG
- **Diseño de código:** Page Object Model (POM)
- **Ejecución remota:** BrowserStack
- **Runtime:** Node.js (para Appium)
- **Mobile SDK:** Android SDK + ADB + Android Emulator

---

## ⚙️ Configuración Inicial

### 1️⃣ Instalar Node.js (para Appium)
Descarga e instala Node.js desde [nodejs.org](https://nodejs.org/). Esto es necesario para ejecutar Appium.

    node -v
    npm -v

### 2️⃣ Instalar Appium

    npm install -g appium
    appium -v
    appium driver install uiautomator2

### 3️⃣ Instalar Android Studio + SDK
Descarga e instala Android Studio desde [developer.android.com](https://developer.android.com/studio).
  Durante la instalación incluir:
- Android SDK
- Android Emulator
- Android Virtual Device (AVD)
    - Tools → Device Manager → Create device
    - Device: Ej: Pixel 5 / Pixel 6
    - System Image: Android API 33 / 34

### 4️⃣ Configurar Variables de Entorno

    C:\Users\<USER>\AppData\Local\Android\Sdk\emulator
    C:\Users\<USER>\AppData\Local\Android\Sdk\platform-tools
Verificar con:

    where emulator
    where adb

### 5️⃣ Instalar y Configurar Java JDK
Descarga e instala Java JDK 17+ desde (https://adoptium.net/).

Variables de entorno:

    JAVA_HOME = C:\Program Files\Eclipse Adoptium\jdk-17.x.x
    PATH = %JAVA_HOME%\bin

Verificar con:

    java -version
    javac -version
    echo %JAVA_HOME%

### 6️⃣ Verificar Emulador y ADB
Inicia el emulador desde Android Studio o con:

    emulator -avd <Device_name>
    adb devices

Resultado esperado:

    emulator -list-avds
    List of devices attached
    emulator-5554   device

### 🔧 Appium Local
Antes de ejecutar pruebas mobile, asegúrate de que Appium esté corriendo:

    appium --base-path /wd/hub --allow-cors
    emulator -avd <Device_name>

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

### 🤖 Pruebas Mobile Local Android
```mvn clean test -DsuiteXmlFile=testng-mobile.xml```

### 🤖 Pruebas Mobile en BrowserStack Android
```mvn clean test -DsuiteXmlFile=testng-mobile.xml -Dbrowserstack=true```

### 🍎 Pruebas Mobile Local iOS
```mvn clean test -DsuiteXmlFile=testng-mobile-iOS.xml```

### 🍎 Pruebas Mobile en BrowserStack iOS
```mvn clean test -DsuiteXmlFile=testng-mobile-iOS.xml -Dbrowserstack=true```

## 📚 Reportes Generados
### WEB
- **target/cucumber-html-reports/web/web-report.html**

### Mobile
- **target/cucumber-html-reports/mobile/android/android-report.html**
- **target/cucumber-html-reports/mobile/iOS/iOS-report.html**