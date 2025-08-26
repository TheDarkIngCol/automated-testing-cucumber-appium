@android @E2E @compra
Feature: Flujo de compra en la aplicación

  Scenario: Flujo completo de compra
    Given ingreso usuario y contrasena correctos en mobile
    And estoy en la pagina de productos
    When selecciono un producto y lo agrego al carrito en mobile
    Then el producto aparece en el carrito en mobile
