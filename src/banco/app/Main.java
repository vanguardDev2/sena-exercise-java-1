package banco.app;

import banco.modelo.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Cliente c1 = new Cliente("u010", "Ana");
        Cliente c2 = new Cliente("u020", "Ben");


        // Subclases concretas
        Cuenta a1 = new CuentaAhorro("AH-1", c1, 0, 6.0);  // 6% anual
        Cuenta i1 = new CuentaInversion("IV-1", c2, 0);

        List<Cuenta> cuentas = Arrays.asList(a1, i1);

        // Depósitos iniciales (respetando reglas)
        System.out.println("Depósito ahorro 1000 (ok): " + a1.depositar(1000, "u010"));
        System.out.println("Depósito inversión 25000 (ok): " + i1.depositar(25_000, "u020"));

        // PRUEBAS DE REGLAS
        // Ahorro: no puede quedar < 500
        System.out.println("Retiro ahorro 600 (deja 400: falla): " + a1.retirar(600, "u010", true));
        System.out.println("Retiro ahorro 400 (deja 600: ok): " + a1.retirar(400, "u010", true));

        // Inversión: no < 10,000 (salvo cancelación total)
        System.out.println("Retiro inv 16000 (deja 9000: falla): " + i1.retirar(16_000, "u020", true));
        System.out.println("Retiro inv 15000 (deja 10000: ok): " + i1.retirar(15_000, "u020", true));


        if (a1 instanceof CuentaAhorro ca) {
            double interes = ca.aplicarInteresMensual();
            System.out.println("Interés acreditado ahorro: " + interes);
        }


        System.out.println("Cancelación total inversión (ok): " + i1.retirar(i1.getSaldo(), "u020", true));

        // Mostrar saldos finales (polimorfismo: ambas son 'Cuenta')
        for (Cuenta cta : cuentas) {
            System.out.println(cta.getNumero() + " saldo final: " + cta.getSaldo());
        }


        System.out.println("Total cuentas (static): " + Cuenta.getTotalCuentas());


        System.out.println("\n--- DEMO EMPLEADOS ---");

        Empleado emp1 = new Cajero("e001", "Carla", 2_000_000, 1);
        Empleado emp2 = new Supervisor("e002", "Luis", 4_000_000, 5);
        Empleado emp3 = new Recepcionista("e003", "Iris", 1_800_000, 10);

        java.util.List<Empleado> staff = java.util.List.of(emp1, emp2, emp3);

// Polimorfismo: cada uno implementa su bono anual
        for (Empleado e : staff) {
            double sueldoOk = e.cobrarSueldo(e.getNumero());  // correcto
            double sueldoFail = e.cobrarSueldo("id-incorrecta"); // debe dar 0.0
            System.out.printf("%s (%s) -> vacaciones=%d días, bonoAnual=%.2f, cobrarOK=%.2f, cobrarFAIL=%.2f%n",
                    e.getNombre(), e.getPuesto(),
                    e.calcularDiasVacaciones(),
                    e.calcularBonoAnual(),
                    sueldoOk,
                    sueldoFail);
        }

// Variables/métodos de clase (static)
        System.out.println("Total empleados: " + Empleado.getTotalEmpleados());
        System.out.println("Cajeros: " + Cajero.getTotalCajeros()
                + " | Supervisores: " + Supervisor.getTotalSupervisores()
                + " | Recepcionistas: " + Recepcionista.getTotalRecepcionistas());

        // --- DEMO RECOMENDACIÓN DE INVERSIÓN ---
        System.out.println("\n--- DEMO RECOMENDACIÓN DE INVERSIÓN ---");


        CasaInversion.setUmbralConfiabilidad(0.75);


        CasaInversion acme = new CasaInversion(
                "C-ACME", "ACME Capital", NivelRiesgo.MEDIO,
                0.80, 12.0,     // confiabilidad 80%, 12% anual
                5_000,          // monto mínimo
                6               // plazo mínimo en meses
        );
        CasaInversion safe = new CasaInversion(
                "C-SAFE", "SafeFunds", NivelRiesgo.BAJO,
                0.72, 8.0,
                2_000,
                3
        );
        CasaInversion grow = new CasaInversion(
                "C-GROW", "GrowPlus", NivelRiesgo.ALTO,
                0.85, 18.0,
                20_000,
                12
        );

        java.util.List<CasaInversion> casas = java.util.List.of(acme, safe, grow);

// Preferencia del usuario/cliente:
        double presupuesto = 15_000;
        int plazoMeses = 12;
        NivelRiesgo maxRiesgo = NivelRiesgo.MEDIO; // el cliente acepta hasta MEDIO

        CasaInversion recomendada = RecomendadorInversion.recomendar(casas, presupuesto, plazoMeses, maxRiesgo);

        System.out.println("Total casas registradas: " + CasaInversion.getTotalCasas());
        if (recomendada != null) {
            double gan = recomendada.estimarGanancia(presupuesto, plazoMeses);
            double cap = recomendada.estimarCapitalFinal(presupuesto, plazoMeses);
            System.out.printf("Recomendada: %s (%s) | Riesgo=%s | Ganancia=%.2f | Capital final=%.2f%n",
                    recomendada.getClave(), recomendada.getNombre(), recomendada.getNivelRiesgo(), gan, cap);
        } else {
            System.out.println("No hay opción que cumpla confiabilidad, riesgo y mínimos.");
        }
        System.out.println("Total recomendaciones efectuadas: " + RecomendadorInversion.getTotalRecomendaciones());


    }
}
