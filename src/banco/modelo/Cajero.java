package banco.modelo;

public class Cajero extends Empleado {
    // Variable y método de clase (static) para el requisito
    private static int totalCajeros = 0;
    public static int getTotalCajeros() { return totalCajeros; }

    public Cajero(String numero, String nombre, double salarioMensual, int aniosServicio) {
        super(numero, nombre, "Cajero", salarioMensual, aniosServicio);
        totalCajeros++;
    }

    @Override
    public double calcularBonoAnual() {
        // política de ejemplo: 30% del salario mensual
        return salarioMensual * 0.30;
    }
}
