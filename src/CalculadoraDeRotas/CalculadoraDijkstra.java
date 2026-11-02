package CalculadoraDeRotas;

public class CalculadoraDijkstra {

    private MapaCidade mapa;
    private int[] anterior;

    public CalculadoraDijkstra(MapaCidade mapa) {
        this.mapa = mapa;
    }

    public double calcularMenorTempo(int origem, int destino) {

        int totalLocais = mapa.getTotalLocais();

        double[] menorTempo = new double[totalLocais];
        anterior = new int[totalLocais];
        boolean[] visitados = new boolean[totalLocais];

        for (int i = 0; i < totalLocais; i++) {
            menorTempo[i] = MapaCidade.INF;
            anterior[i] = -1;
        }

        menorTempo[origem] = 0;
        boolean continuar = true;

        for (int i = 0; i < totalLocais && continuar; i++) {

            int atual = -1;

            for (int j = 0; j < totalLocais; j++) {
                if (!visitados[j] &&
                        (atual == -1 || menorTempo[j] < menorTempo[atual])) {
                    atual = j;
                }
            }

            if (atual == -1 || menorTempo[atual] == MapaCidade.INF) {
                continuar = false;
            } else {

                visitados[atual] = true;

                if (atual == destino) {
                    continuar = false;
                } else {

                    for (int j = 0; j < totalLocais; j++) {

                        double tempoLigacao = mapa.getTempo(atual, j);

                        if (!visitados[j] && tempoLigacao != MapaCidade.INF) {

                            double novoTempo = menorTempo[atual] + tempoLigacao;

                            if (novoTempo < menorTempo[j]) {
                                menorTempo[j] = novoTempo;

                                anterior[j] = atual;
                            }
                        }
                    }
                }
            }
        }

        return menorTempo[destino];
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