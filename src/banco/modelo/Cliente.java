package banco.modelo;

public class Cliente {
    private final String numeroUsuario;
    private final String nombre;

    private static int totalClientes = 0;

    public Cliente(String numeroUsuario, String nombre) {
        this.numeroUsuario = numeroUsuario;
        this.nombre = nombre;
        totalClientes++;
    }

    public String getNumeroUsuario() { return numeroUsuario; }
    public String getNombre() { return nombre; }

    // Método de clase (static)
    public static int getTotalClientes() { return totalClientes; }
}
