package CalculadoraDeRotas;

public class CalculadoraDijkstra {

    private MapaCidade mapa;
    private int[] anterior;

    public CalculadoraDijkstra(MapaCidade mapa) {
        this.mapa = mapa;
    }

    public double calcularMenorTempo(int origem, int destino) {

        int totalLocais = mapa.getTotalLocais();

        double[] recordeTempo = new double[totalLocais];
        anterior = new int[totalLocais];
        boolean[] cidadesVisitadas = new boolean[totalLocais];

        for (int i = 0; i < totalLocais; i++) {
            recordeTempo[i] = MapaCidade.INF;
            anterior[i] = -1;
        }

        recordeTempo[origem] = 0;
        boolean continuar = true;

        for (int i = 0; i < totalLocais && continuar; i++) {

            int cidadeAtual = -1;

            for (int j = 0; j < totalLocais; j++) {
                if (!cidadesVisitadas[j] && (cidadeAtual == -1 || recordeTempo[j] < recordeTempo[cidadeAtual])) {
                    cidadeAtual = j;
                }
            }

            if (cidadeAtual == -1 || recordeTempo[cidadeAtual] == MapaCidade.INF) {
                continuar = false;
            } else {

                cidadesVisitadas[cidadeAtual] = true;

                if (cidadeAtual == destino) {
                    continuar = false;
                } else {

                    for (int j = 0; j < totalLocais; j++) {

                        double tempoLigacao = mapa.getTempo(cidadeAtual, j);

                        if (!cidadesVisitadas[j] && tempoLigacao != MapaCidade.INF) {

                            double novoTempo = recordeTempo[cidadeAtual] + tempoLigacao;

                            if (novoTempo < recordeTempo[j]) {
                                recordeTempo[j] = novoTempo;

                                anterior[j] = cidadeAtual;
                            }
                        }
                    }
                }
            }
        }

        return recordeTempo[destino];
    }

    public void mostrarCaminho(int destino) {

        int[] caminho = new int[mapa.getTotalLocais()];
        int quantidade = 0;
        int atual = destino;

        while (atual != -1) {
            caminho[quantidade] = atual;
            quantidade++;
            atual = anterior[atual];
        }

        System.out.print("Caminho percorrido: ");

        for (int i = quantidade - 1; i >= 0; i--) {
            System.out.print(mapa.getNomeLocal(caminho[i]));

            if (i > 0) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }
}