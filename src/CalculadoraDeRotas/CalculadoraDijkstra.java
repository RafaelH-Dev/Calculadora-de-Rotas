
package CalculadoraDeRotas;

public class CalculadoraDijkstra {

    private MapaCidade mapa;

    public CalculadoraDijkstra(MapaCidade mapa) {
        this.mapa = mapa;
    }

    public void calcularMenorTempo(int origem, int destino) {

        int totalLocais = mapa.getTotalLocais();

        double[] menorTempo = new double[totalLocais];

        for (int i = 0; i < totalLocais; i++) {

            menorTempo[i] = 999;

        }

        menorTempo[origem] = 0;

        boolean[] visitados = new boolean[totalLocais];

        boolean continuar = true;

        for (int i = 0; i < totalLocais && continuar == true; i++) {

            int atual = -1;

            for (int j = 0; j < totalLocais; j++) {

                if (visitados[j] == false &&
                        (atual == -1 || menorTempo[j] < menorTempo[atual])) {

                    atual = j;
                }
            }

            if (atual == -1 || menorTempo[atual] == 999) {

                continuar = false;

            } else {

                visitados[atual] = true;

                if (atual == destino) {

                    continuar = false;

                } else {

                    for (int j = 0; j < totalLocais; j++) {

                        double tempoLigacao = mapa.getTempo(atual, j);

                        if (tempoLigacao != 999) {

                            double novoTempo =
                                    menorTempo[atual] + tempoLigacao;

                            if (novoTempo < menorTempo[j]) {

                                menorTempo[j] = novoTempo;
                            }
                        }
                    }
                }
            }
        }

        if (menorTempo[destino] == 999) {

            System.out.println("Não existe rota para esse destino.");

        } else {

            System.out.println("Menor tempo: " + menorTempo[destino]);
        }
    }
}
