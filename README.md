# BancoUML — Ejercicio de POO en Java

Pequeño proyecto didáctico que modela un **banco** usando **UML** y **Programación Orientada a Objetos** en Java. Incluye **abstracción**, **herencia**, **polimorfismo** y **miembros de clase (`static`)**. Se implementan clientes, cuentas (ahorro e inversión), empleados (cajero, supervisor, recepcionista) y casas de inversión con niveles de riesgo, más una demo de uso por consola.

## Qué muestra
- **Dominio**: `Cliente`, `Cuenta` (abstracta), `CuentaAhorro`, `CuentaInversion`.
- **Reglas**:
  - Ahorro: primer depósito = **$1,000**, nunca bajar de **$500**.
  - Inversión: primer depósito = **$25,000**, saldo mínimo **$10,000** (salvo **cancelación total**).
  - Operaciones requieren **número de usuario** y en retiros **identificación**.
  - Ahorro aplica **interés mensual** desde un porcentaje anual configurado.
- **Recursos humanos**: `Empleado` (abstracta) + `Cajero`, `Supervisor`, `Recepcionista`; **vacaciones** (5 días el primer año, +2 por año, tope 20), **bono** polimórfico y cobro de sueldo con verificación.
- **Inversiones**: `CasaInversion`, `NivelRiesgo`, `RecomendadorInversion` con **umbral global de confiabilidad** (`static`) y estimación de rendimiento.

## Estructura
- `banco.modelo/` → clases de dominio.
- `banco.app/Main` → demo por consola (crea clientes/cuentas, prueba reglas y recomendaciones).

## Requisitos y ejecución
- **JDK 17+**. Abre en IntelliJ IDEA o compila por línea de comandos.
- Ejecuta `banco.app.Main` para ver la demo y la salida de ejemplo.

> Objetivo educativo: practicar modelado de clases, encapsulación, herencia/abstracción, polimorfismo y uso de miembros de clase (`static`) aplicados a reglas reales de negocio.
