package CalculadoraDeRotas;

public class MapaCidade {
    private static final double INF = 999.0;
    private String[] nomeLocal;
    private double[][] matrizDistancia;
    private double[][] matrizTempo;

    public MapaCidade() {
        nomeLocal = new String[]{
                "São Mamede",
                "Cogumelândia",
                "Night City",
                "Vice City",
                "Pombal",
        };
        matrizDistancia = new double[][]{
                {0.0, 8.0, 67.0, 42.0, 130.0},
                {8.0, 0.0, 70.0, INF, 120.0},
                {67.0, 70.0, 0.0, 30.0, INF},
                {42.0, INF, 30.0, 0.0, 85.0},
                {130.0, 120.0, INF, 85.0, 0.0}
        };
        this.matrizTempo = calculaTempo();
    }

    private double[][] calculaTempo() {
        double[][] tempo = new double[nomeLocal.length][nomeLocal.length];
        for (int i = 0; i < nomeLocal.length; i++) {
            for (int j = 0; j < nomeLocal.length; j++) {
                if (matrizDistancia[i][j] != INF) {
                    tempo[i][j] = matrizDistancia[i][j] * 1.2;
                }
            }
        }
        return tempo;
    }

    public String getNomeLocal(int indice) {
        return nomeLocal[indice];
    }

    public int getTotalLocais() {
        return nomeLocal.length;
    }

    public double getDistancia(int origem, int destino) {
        return matrizDistancia[origem][destino];
    }

    public double getTempo(int origem, int destino) {
        return matrizTempo[origem][destino];
    }
}