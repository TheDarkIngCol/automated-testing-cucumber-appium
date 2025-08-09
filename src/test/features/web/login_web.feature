@web
Feature: Login en la aplicación Web

  @web_exitoso
  Scenario: Login exitoso con usuario y contraseña correctos en web
    When ingreso usuario y contraseña correctos en web

  @web_fallido
  Scenario: Login fallido con usuario y contraseña incorrectos en web
    When ingreso usuario y contraseña incorrecto en web
    Then debe aparecer un mensaje de error en web