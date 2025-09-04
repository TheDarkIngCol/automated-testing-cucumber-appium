@E2E @web @compra
Feature: Flujo de compra en la aplicación

  Scenario: Flujo completo de compra
    Given ingreso usuario y contraseña correctos en web
    When selecciono varios producto y lo agrego al carrito
    Then el producto aparece en el carrito
    And procedo a realizar el checkout