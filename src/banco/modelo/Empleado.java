package banco.modelo;

public abstract class Empleado {
    protected final String numero;     // identificación para cobrar sueldo
    protected final String nombre;
    protected final String puesto;
    protected double salarioMensual;
    protected int aniosServicio;

    // Variable y método de clase (static)
    private static int totalEmpleados = 0;
    public static int getTotalEmpleados() { return totalEmpleados; }

    protected Empleado(String numero, String nombre, String puesto, double salarioMensual, int aniosServicio) {
        if (aniosServicio < 0) throw new IllegalArgumentException("Años de servicio no puede ser negativo.");
        if (salarioMensual < 0) throw new IllegalArgumentException("Salario no puede ser negativo.");
        this.numero = numero;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salarioMensual = salarioMensual;
        this.aniosServicio = aniosServicio;
        totalEmpleados++;
    }

    public String getNumero() { return numero; }
    public String getNombre() { return nombre; }
    public String getPuesto() { return puesto; }
    public double getSalarioMensual() { return salarioMensual; }
    public int getAniosServicio() { return aniosServicio; }

    // Vacaciones: 5 días el primer año; +2 por año hasta 20 días
    public int calcularDiasVacaciones() {
        if (aniosServicio < 1) return 0;
        int dias = 5 + 2 * (aniosServicio - 1);
        return Math.min(dias, 20);
    }

    // Cobro de sueldo: requiere identificación (numero)
    public double cobrarSueldo(String numeroEmpleado) {
        if (!this.numero.equals(numeroEmpleado)) return 0.0;
        return this.salarioMensual;
    }

    // Polimorfismo: cada puesto define su bono anual
    public abstract double calcularBonoAnual();
}
