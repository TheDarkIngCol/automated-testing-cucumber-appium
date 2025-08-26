@android
Feature: Login en la aplicación Mobile

  @mobile_exitoso_android
  Scenario: Login exitoso con usuario y contrasena correctos en mobile
    When ingreso usuario y contrasena correctos en mobile

  @mobile_fallido_android
  Scenario: Login fallido con usuario y contrasena incorrectos en mobile
    When ingreso usuario y contrasena incorrecto en mobile
    Then debe aparecer un mensaje de error
