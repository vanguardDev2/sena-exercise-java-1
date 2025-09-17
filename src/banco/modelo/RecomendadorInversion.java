package banco.modelo;

import java.util.List;

public class RecomendadorInversion {
    // Variable y método de clase (static) para el requisito
    private static int totalRecomendaciones = 0;
    public static int getTotalRecomendaciones() { return totalRecomendaciones; }

    /**
     * Elige la mejor casa de inversión según:
     * - Cumple política de confiabilidad global (static en CasaInversion).
     * - Riesgo <= maxRiesgo permitido por el usuario.
     * - Respeta monto y plazo mínimos (valida con estimarGanancia > 0).
     * - Maximiza la ganancia estimada (interés simple de aprendizaje).
     */
    public static CasaInversion recomendar(List<CasaInversion> casas,
                                           double monto,
                                           int meses,
                                           NivelRiesgo maxRiesgo) {
        CasaInversion mejor = null;
        double mejorGanancia = Double.NEGATIVE_INFINITY;

        for (CasaInversion c : casas) {
            if (!c.esConfiableSegunPolitica()) continue;
            if (c.getNivelRiesgo().ordinal() > maxRiesgo.ordinal()) continue;

            double gan = c.estimarGanancia(monto, meses); // valida mínimos
            if (gan <= 0) continue;

            if (gan > mejorGanancia) {
                mejorGanancia = gan;
                mejor = c;
            }
        }

        if (mejor != null) totalRecomendaciones++;
        return mejor;
    }
}
