package banco.modelo;

public class CuentaAhorro extends Cuenta {


    private final double porcentajeAhorroAnual;


    private static int totalCuentasAhorro = 0;
    public static int getTotalCuentasAhorro() { return totalCuentasAhorro; }

    public CuentaAhorro(String numero, Cliente titular, double saldoInicial, double porcentajeAhorroAnual) {
        super(numero, titular, saldoInicial);
        if (porcentajeAhorroAnual < 0) {
            throw new IllegalArgumentException("El porcentaje anual no puede ser negativo.");
        }
        this.porcentajeAhorroAnual = porcentajeAhorroAnual;
        totalCuentasAhorro++;
    }

    public double getPorcentajeAhorroAnual() { return porcentajeAhorroAnual; }

    @Override
    public boolean depositar(double monto, String numeroUsuario) {

        if (!titular.getNumeroUsuario().equals(numeroUsuario)) return false;
        if (monto <= 0) return false;


        if (this.saldo == 0 && Math.abs(monto - 1000.0) > 0.00001) return false;

        this.saldo += monto;
        return true;
    }

    @Override
    public boolean retirar(double monto, String numeroUsuario, boolean presentoIdentificacion) {

        if (!titular.getNumeroUsuario().equals(numeroUsuario)) return false;
        if (!presentoIdentificacion) return false;
        if (monto <= 0) return false;

        double nuevoSaldo = this.saldo - monto;

        if (nuevoSaldo < 500.0) return false;

        this.saldo = nuevoSaldo;
        return true;
    }


    public double aplicarInteresMensual() {
        double interes = this.saldo * (porcentajeAhorroAnual / 100.0) / 12.0;
        this.saldo += interes;
        return interes;
    }
}
