package banco.modelo;

public class Recepcionista extends Empleado {
    private static int totalRecepcionistas = 0;
    public static int getTotalRecepcionistas() { return totalRecepcionistas; }

    public Recepcionista(String numero, String nombre, double salarioMensual, int aniosServicio) {
        super(numero, nombre, "Recepcionista", salarioMensual, aniosServicio);
        totalRecepcionistas++;
    }

    @Override
    public double calcularBonoAnual() {
        return salarioMensual * 0.20;
    }
}
