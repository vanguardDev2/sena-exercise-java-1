package banco.modelo;

public abstract class Cuenta {
    protected final String numero;
    protected final Cliente titular;
    protected double saldo;


    private static int totalCuentas = 0;

    protected Cuenta(String numero, Cliente titular, double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
        totalCuentas++;
    }


    public abstract boolean depositar(double monto, String numeroUsuario);
    public abstract boolean retirar(double monto, String numeroUsuario, boolean presentoIdentificacion);

    public double getSaldo() { return saldo; }
    public String getNumero() { return numero; }
    public Cliente getTitular() { return titular; }


    public static int getTotalCuentas() { return totalCuentas; }
}
