package banco.modelo;


public class CasaInversion {

    private final String clave;                 // identificador único que usa el banco
    private final String nombre;
    private final NivelRiesgo nivelRiesgo;      // BAJO, MEDIO o ALTO
    private final double confiabilidad;         // 0.0 a 1.0 (ej. 0.85 = 85%)
    private final double porcentajeRetornoAnual; // ej. 10.0 => 10% anual (bruto, simplificado)
    private final double montoMinimo;           // inversión mínima aceptada
    private final int plazoMinimoMeses;         // plazo mínimo en meses


    private static int totalCasas = 0;
    public static int getTotalCasas() { return totalCasas; }


    private static double UMBRAL_CONFIABILIDAD = 0.70; // 70% por defecto
    public static double getUmbralConfiabilidad() { return UMBRAL_CONFIABILIDAD; }
    public static void setUmbralConfiabilidad(double nuevoUmbral) {
        if (nuevoUmbral < 0 || nuevoUmbral > 1) throw new IllegalArgumentException("Umbral inválido (0..1).");
        UMBRAL_CONFIABILIDAD = nuevoUmbral;
    }

    public CasaInversion(String clave,
                         String nombre,
                         NivelRiesgo nivelRiesgo,
                         double confiabilidad,            // 0..1
                         double porcentajeRetornoAnual,   // 0..100
                         double montoMinimo,
                         int plazoMinimoMeses) {
        if (clave == null || clave.isBlank()) throw new IllegalArgumentException("Clave requerida.");
        if (confiabilidad < 0 || confiabilidad > 1) throw new IllegalArgumentException("Confiabilidad 0..1.");
        if (porcentajeRetornoAnual < 0) throw new IllegalArgumentException("% retorno anual no puede ser negativo.");
        if (montoMinimo < 0) throw new IllegalArgumentException("Monto mínimo no puede ser negativo.");
        if (plazoMinimoMeses < 0) throw new IllegalArgumentException("Plazo mínimo inválido.");

        this.clave = clave;
        this.nombre = nombre;
        this.nivelRiesgo = nivelRiesgo;
        this.confiabilidad = confiabilidad;
        this.porcentajeRetornoAnual = porcentajeRetornoAnual;
        this.montoMinimo = montoMinimo;
        this.plazoMinimoMeses = plazoMinimoMeses;

        totalCasas++;
    }

    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
    public NivelRiesgo getNivelRiesgo() { return nivelRiesgo; }
    public double getConfiabilidad() { return confiabilidad; }
    public double getPorcentajeRetornoAnual() { return porcentajeRetornoAnual; }
    public double getMontoMinimo() { return montoMinimo; }
    public int getPlazoMinimoMeses() { return plazoMinimoMeses; }


    public boolean esConfiableSegunPolitica() {
        return this.confiabilidad >= UMBRAL_CONFIABILIDAD;
    }


    public double estimarGanancia(double monto, int meses) {
        if (monto < montoMinimo) return 0.0;
        if (meses < plazoMinimoMeses) return 0.0;
        double tasaMensual = (porcentajeRetornoAnual / 100.0) / 12.0;
        return monto * tasaMensual * meses; // interés simple (sencillo para empezar)
    }


    public double estimarCapitalFinal(double monto, int meses) {
        return monto + estimarGanancia(monto, meses);
    }
}
