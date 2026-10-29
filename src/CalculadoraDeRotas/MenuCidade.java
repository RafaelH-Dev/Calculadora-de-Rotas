package CalculadoraDeRotas;

import java.util.Scanner;

public class MenuCidade {
    private MapaCidade mapa;
    private CalculadoraDijkstra dijkstra;
    private Scanner input;

    public MenuCidade() {
        this.mapa = new MapaCidade();
        this.dijkstra = new CalculadoraDijkstra(mapa);
        this.input = new Scanner(System.in);
    }

    public void executar() {
        int opcao = -1;

        do {
            System.out.println("\n========================================");
            System.out.println("           -    MENU    - ");
            System.out.println("========================================");
            System.out.println("1. Listar Cidades e Índices");
            System.out.println("2. Adicionar Nova Cidade");
            System.out.println("3. Remover Cidade (Personalizada)");
            System.out.println("4. Definir Conexão (Estrada / Distância)");
            System.out.println("5. Calcular Menor Tempo de Rota");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1: {
                    mapa.listarCidades();
                    break;
                }

                case 2: {
                    System.out.print("\nDigite o nome da nova cidade: ");
                    String novaCidade = input.nextLine();
                    boolean adicionou = mapa.adicionarCidade(novaCidade);
                    if (adicionou) {
                        System.out.printf("Sucesso! A cidade '%s' foi adicionada.\n", novaCidade);
                    } else {
                        System.out.println("Erro: A cidade já existe ou o nome é inválido.");
                    }
                    break;
                }

                case 3: {
                    mapa.listarCidades();
                    System.out.println("SÓ É PERMITIDO A REMOÇÃO DE CIDADES ADICIONADAS PELO USUÁRIO!!!\n");
                    System.out.print("\nDigite o nome exato da cidade que deseja remover: ");
                    String cidadeRemover = input.nextLine();
                    boolean removeu = mapa.removerCidade(cidadeRemover);
                    if (removeu) {
                        System.out.println("Sucesso! A cidade foi removida.");
                    } else {
                        System.out.println("Erro: Não foi possível remover (cidade não encontrada ou pertence às 5 primeiras fixas).");
                    }
                    break;
                }

                case 4: {
                    mapa.listarCidades();
                    System.out.print("Digite o índice da cidade de ORIGEM: ");
                    int pontoDePartida = input.nextInt();
                    System.out.print("Digite o índice da cidade de DESTINO: ");
                    int localDeDestino = input.nextInt();

                    if (pontoDePartida >= 0 && pontoDePartida < mapa.getTotalLocais() && localDeDestino >= 0 && localDeDestino < mapa.getTotalLocais()) {
                        System.out.println("Digite:\n1 - Remover estrada\n2 - Adicionar/Atualizar estrada");
                        int estradaOpcao = input.nextInt();

                        double distanciaEstrada;

                        if (estradaOpcao == 1) {
                            distanciaEstrada = MapaCidade.INF;
                            mapa.definirConexao(pontoDePartida, localDeDestino, distanciaEstrada);
                            System.out.println("Estrada removida com sucesso!");
                        } else if (estradaOpcao == 2) {
                            System.out.print("Digite a distância em km: ");
                            distanciaEstrada = input.nextDouble();
                            mapa.definirConexao(pontoDePartida, localDeDestino, distanciaEstrada);
                            System.out.println("Conexão atualizada com sucesso!");
                        } else {
                            System.out.println("Opção inválida!");
                        }
                        input.nextLine();
                    } else {
                        System.out.println("Erro: Índices de cidades inválidos!");
                        input.nextLine();
                    }
                    break;
                }

                case 5: {
                    mapa.listarCidades();
                    System.out.print("Digite o índice da cidade de ORIGEM: ");
                    int pontoDePartida = input.nextInt();
                    System.out.print("Digite o índice da cidade de DESTINO: ");
                    int localDeDestino = input.nextInt();
                    input.nextLine();

                    if (pontoDePartida >= 0 && pontoDePartida < mapa.getTotalLocais() && localDeDestino >= 0 && localDeDestino < mapa.getTotalLocais()) {
                        double tempoTotal = dijkstra.calcularMenorTempo(pontoDePartida, localDeDestino);
                        String nomeOrigem = mapa.getNomeLocal(pontoDePartida);
                        String nomeDestino = mapa.getNomeLocal(localDeDestino);

                        System.out.println("\n----------------------------------------");
                        if (tempoTotal == MapaCidade.INF) {
                            System.out.printf("Não existe caminho de %s até %s.\n", nomeOrigem, nomeDestino);
                        } else {
                            System.out.printf("Menor tempo de %s para %s: %.2f unidades.\n", nomeOrigem, nomeDestino, tempoTotal);
                        }
                        System.out.println("----------------------------------------");
                    } else {
                        System.out.println("Erro: Índices inválidos!");
                    }
                    break;
                }

                case 0: {
                    System.out.println("Encerrando a Calculadora de Rotas. Boa viagem! (; ");
                    break;
                }

                default: {
                    System.out.println("Opção inválida! Escolha um número entre 0 e 5.");
                }
            }

        } while (opcao != 0);

        input.close();
    }
}