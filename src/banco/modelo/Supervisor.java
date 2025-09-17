package banco.modelo;

public class Supervisor extends Empleado {
    private static int totalSupervisores = 0;
    public static int getTotalSupervisores() { return totalSupervisores; }

    public Supervisor(String numero, String nombre, double salarioMensual, int aniosServicio) {
        super(numero, nombre, "Supervisor", salarioMensual, aniosServicio);
        totalSupervisores++;
    }

    @Override
    public double calcularBonoAnual() {
        return salarioMensual * 0.60;
    }
}
