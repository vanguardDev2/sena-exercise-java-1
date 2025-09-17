package banco.modelo;

public class CuentaInversion extends Cuenta {


    public static final double MONTO_INICIAL_OBLIGATORIO = 25_000.0;
    public static final double SALDO_MINIMO_SIN_CANCELAR = 10_000.0;

    private static int totalCuentasInversion = 0;
    public static int getTotalCuentasInversion() { return totalCuentasInversion; }


    public CuentaInversion(String numero, Cliente titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
        if (saldoInicial != 0.0 && Math.abs(saldoInicial - MONTO_INICIAL_OBLIGATORIO) > 0.00001) {
            throw new IllegalArgumentException("El saldo inicial de inversión debe ser exactamente $25,000 o 0.");
        }
        totalCuentasInversion++;
    }

    @Override
    public boolean depositar(double monto, String numeroUsuario) {
        if (!titular.getNumeroUsuario().equals(numeroUsuario)) return false;
        if (monto <= 0) return false;


        if (this.saldo == 0.0 && Math.abs(monto - MONTO_INICIAL_OBLIGATORIO) > 0.00001) return false;

        this.saldo += monto;
        return true;
    }

    @Override
    public boolean retirar(double monto, String numeroUsuario, boolean presentoIdentificacion) {
        if (!titular.getNumeroUsuario().equals(numeroUsuario)) return false;
        if (!presentoIdentificacion) return false;
        if (monto <= 0) return false;

        double nuevoSaldo = this.saldo - monto;


        boolean esCancelacionTotal = Math.abs(monto - this.saldo) <= 0.00001;
        if (esCancelacionTotal) {
            this.saldo = 0.0;
            return true;
        }


        if (nuevoSaldo < SALDO_MINIMO_SIN_CANCELAR) return false;

        this.saldo = nuevoSaldo;
        return true;
    }
}
