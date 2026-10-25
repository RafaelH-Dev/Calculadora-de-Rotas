package CalculadoraDeRotas;

public class CalculadoraDijkstra{

   private MapaCidade mapa;

    public CalculadoraDijkstra(MapaCidade mapa){
        this.mapa = mapa;
    }
    public void calculadoraDijkstra(int origem, int destino) {
        int totalLocais =  mapa.getTotalLocais();
        double[] listaMenorCusto = new double[totaLocais];
        boolean[] listaVisitados = new boolean[totaLocais];
    }

}