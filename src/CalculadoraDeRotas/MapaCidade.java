package CalculadoraDeRotas;

public class MapaCidade {
    public static final double INF = 999.0;
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
                }else{
                    tempo[i][j] = INF;
                }
            }
        }
        return tempo;
    }

    public boolean adicionarCidade(String nomeCidade) {
        boolean teste = verificaRepetido(nomeCidade);

        if (teste) {
            int numeroAntigoCidades = nomeLocal.length;
            nomeLocal = expandirVetor(nomeLocal, nomeCidade);
            int numeroNovoCidades = nomeLocal.length;

            double[][] matrizDistanciaNova = new double[numeroNovoCidades][numeroNovoCidades];
            double[][] matrizNovoTempo = new double[numeroNovoCidades][numeroNovoCidades];

            for (int i = 0; i < numeroNovoCidades; i++) {
                for (int j = 0; j < numeroNovoCidades; j++) {
                    if (i == j) {
                        matrizDistanciaNova[i][j] = 0;
                        matrizNovoTempo[i][j] = 0;
                    }else if(i < numeroAntigoCidades && j < numeroAntigoCidades){
                        matrizDistanciaNova[i][j] = matrizDistancia[i][j];
                        matrizNovoTempo[i][j] = matrizTempo[i][j];

                    }else{
                        matrizDistanciaNova[i][j] = INF;
                        matrizNovoTempo[i][j] = INF;
                    }

                }
            }

            matrizDistancia = matrizDistanciaNova;
            matrizTempo = matrizNovoTempo;
            return true;
        }
        return false;
    }

    public boolean removerCidade(String nomeCidade){
        int indiceRemover = -1;

        for(int i = 0; i<nomeLocal.length; i++){
            if(nomeLocal[i].equalsIgnoreCase(nomeCidade)){
                indiceRemover = i;
            }
        }


        if(indiceRemover == -1 || indiceRemover < 5){

            return false;
        }

        int numeroAntigoCidades = nomeLocal.length;
        int numeroNovoCidades = numeroAntigoCidades-1;

        String[] novoVetorCidades = new String[numeroNovoCidades];

        int contadorNovoVetor = 0;


        for(int i = 0; i<numeroAntigoCidades; i++){
            if(i != indiceRemover){
                novoVetorCidades[contadorNovoVetor] =  nomeLocal[i];
                contadorNovoVetor++;
            }
        }
        nomeLocal = novoVetorCidades;

        double[][] matrizDistanciaNova = new double[numeroNovoCidades][numeroNovoCidades];
        double[][] matrizTempoNova = new double[numeroNovoCidades][numeroNovoCidades];
        int novaLinha = 0;
        for(int i = 0; i<numeroAntigoCidades; i++){
            if(indiceRemover!=i){
                int novaColuna = 0;
                for(int j = 0; j<numeroAntigoCidades; j++){
                    if(j != indiceRemover) {
                        matrizDistanciaNova[novaLinha][novaColuna] = matrizDistancia[i][j];
                        matrizTempoNova[novaLinha][novaColuna] = matrizTempo[i][j];
                        novaColuna++;
                    }
                }
                novaLinha++;
            }
        }
        matrizDistancia = matrizDistanciaNova;
        matrizTempo = matrizTempoNova;
        return true;
    }

    public boolean definirConexao(int origem, int destino, double distancia) {
        if (origem < 0 || origem >= nomeLocal.length || destino < 0 || destino >= nomeLocal.length || origem == destino) {
            return false;
        }

        if (origem < 5 && destino < 5) {
            return false;
        }

        matrizDistancia[origem][destino] = distancia;
        matrizDistancia[destino][origem] = distancia;

        if (distancia == INF) {
            matrizTempo[origem][destino] = INF;
            matrizTempo[destino][origem] = INF;
        } else {
            matrizTempo[origem][destino] = distancia * 1.2;
            matrizTempo[destino][origem] = distancia * 1.2;
        }

        return true;
    }

    private boolean verificaRepetido(String novoNome) {
        for (int i = 0; i < nomeLocal.length; i++) {
            if (nomeLocal[i].equalsIgnoreCase(novoNome)) {
                return false;
            }
        }
        return true;
    }

    private String[] expandirVetor(String[] vetorAntigo, String novoNome) {
        int numeroCidadesAntigo = vetorAntigo.length;
        String[] vetorNovo = new String[numeroCidadesAntigo + 1];

        for (int i = 0; i < numeroCidadesAntigo; i++) {
            vetorNovo[i] = vetorAntigo[i];
        }

        vetorNovo[numeroCidadesAntigo] = novoNome;

        return vetorNovo;
    }

    public void listarCidades(){
        System.out.println("\n--- CIDADES DISPONÍVEIS ---");
        for (int i = 0; i < nomeLocal.length; i++) {
            System.out.println("" + i + " " + nomeLocal[i]);
        }
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