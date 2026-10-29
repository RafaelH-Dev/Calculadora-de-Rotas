package CalculadoraDeRotas;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        MapaCidade mapa = new MapaCidade();
        CalculadoraDijkstra calculadora = new CalculadoraDijkstra(mapa);

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n===== CALCULADORA DE ROTAS =====");
            System.out.println("1 - Listar cidades");
            System.out.println("2 - Adicionar cidade");
            System.out.println("3 - Consultar distancia e tempo direto");
            System.out.println("4 - Calcular menor tempo entre cidades");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = leitor.nextInt();
            leitor.nextLine();

            if (opcao == 1) {

                System.out.println("\n===== CIDADES CADASTRADAS =====");

                for (int i = 0; i < mapa.getTotalLocais(); i++) {
                    System.out.println(i + " - " + mapa.getNomeLocal(i));
                }

                System.out.println("Total de cidades: " + mapa.getTotalLocais());

            } else if (opcao == 2) {

                System.out.print("\nDigite o nome da nova cidade: ");
                String nomeCidade = leitor.nextLine().trim();

                if (nomeCidade.isEmpty()) {
                    System.out.println("O nome da cidade nao pode ser vazio.");
                } else if (mapa.adicionarCidade(nomeCidade)) {
                    System.out.println("Cidade adicionada com sucesso!");
                    System.out.println("Novo total de cidades: " + mapa.getTotalLocais());
                } else {
                    System.out.println("Essa cidade ja esta cadastrada.");
                }

            } else if (opcao == 3) {

                System.out.println("\n===== CONSULTAR DISTANCIA E TEMPO =====");

                for (int i = 0; i < mapa.getTotalLocais(); i++) {
                    System.out.println(i + " - " + mapa.getNomeLocal(i));
                }

                System.out.print("Digite o numero da cidade de origem: ");
                int origem = leitor.nextInt();

                System.out.print("Digite o numero da cidade de destino: ");
                int destino = leitor.nextInt();

                if (origem >= 0 && origem < mapa.getTotalLocais()
                        && destino >= 0 && destino < mapa.getTotalLocais()) {

                    double distancia = mapa.getDistancia(origem, destino);
                    double tempo = mapa.getTempo(origem, destino);

                    System.out.println("\nOrigem: " + mapa.getNomeLocal(origem));
                    System.out.println("Destino: " + mapa.getNomeLocal(destino));

                    if (distancia == MapaCidade.INF) {
                        System.out.println("Nao existe ligacao direta entre essas cidades.");
                    } else {
                        System.out.println("Distancia direta: " + distancia);
                        System.out.println("Tempo direto: " + tempo);
                    }

                } else {
                    System.out.println("Numero de cidade invalido!");
                }

                leitor.nextLine();

            } else if (opcao == 4) {

                System.out.println("\n===== MENOR TEMPO DE VIAGEM =====");

                for (int i = 0; i < mapa.getTotalLocais(); i++) {
                    System.out.println(i + " - " + mapa.getNomeLocal(i));
                }

                System.out.print("Digite o numero da cidade de origem: ");
                int origem = leitor.nextInt();

                System.out.print("Digite o numero da cidade de destino: ");
                int destino = leitor.nextInt();

                if (origem >= 0 && origem < mapa.getTotalLocais()
                        && destino >= 0 && destino < mapa.getTotalLocais()) {

                    double menorTempo = calculadora.calcularMenorTempo(origem, destino);

                    System.out.println("\nOrigem: " + mapa.getNomeLocal(origem));
                    System.out.println("Destino: " + mapa.getNomeLocal(destino));

                    if (menorTempo == MapaCidade.INF) {
                        System.out.println("Nao existe rota disponivel entre essas cidades.");
                    } else {
                        System.out.println("Menor tempo de viagem: " + menorTempo);
                    }

                } else {
                    System.out.println("Numero de cidade invalido!");
                }

                leitor.nextLine();

            } else if (opcao == 0) {

                System.out.println("\nPrograma encerrado.");

            } else {

                System.out.println("\nOpcao invalida! Tente novamente.");

            }
        }

        leitor.close();
    }
}