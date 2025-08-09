@mobile
Feature: Login en la aplicación Mobile

  @mobile_exitoso
  Scenario: Login exitoso con usuario y contraseña correctos en mobile
    When ingreso usuario y contraseña correctos en mobile

  @mobile_fallido
  Scenario: Login fallido con usuario y contraseña incorrectos en mobile
    When ingreso usuario y contraseña incorrecto en mobile
    Then debe aparecer un mensaje de error
