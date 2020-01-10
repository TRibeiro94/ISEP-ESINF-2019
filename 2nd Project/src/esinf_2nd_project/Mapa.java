package esinf_2nd_project;

import graphbase.Graph;
import graphbase.GraphAlgorithms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import javafx.util.Pair;
import matrixgraph.AdjacencyMatrix;
import matrixgraph.MatrixAlgorithms;

/**
 *
 * @author Tiago Ribeiro
 * @author Bernardo Carvalho
 */
public class Mapa {

    public ReaderManager reader;
    public Graph<Pais, Integer> grafo;

    /**
     * Construtor da classe central mapa
     */
    public Mapa() {
        this.reader = new ReaderManager(this);
        this.grafo = new Graph<>(false);
    }

    /**
     * Adiciona país, isto é, um vértice ao grafo
     *
     * @param p país a ser inserido
     */
    public void addPais(Pais p) {
        grafo.insertVertex(p);
    }

    /**
     * Adiciona um ramo entre dois países, indicando portanto a existência de uma fronteira entre os 2
     *
     * @param origem país vértice de origem
     * @param destino país vértice de destino
     * @param distancia peso do ramo (distância)
     */
    public void addFronteira(Pais origem, Pais destino, double distancia) {
        grafo.insertEdge(origem, destino, 1, distancia);
    }

    /**
     * Método usado para devolver um País através do seu nome
     *
     * @param name nome do País
     * @return País(objecto)
     */
    public Pais getPaisByNome(String name) {
        for (Pais p : grafo.vertices()) {
            if (p.getNome().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Método usado para devolver um País através da sua capital
     *
     * @param name nome da capital do país
     * @return País(objecto)
     */
    public Pais getPaisByCapital(String name) {
        for (Pais p : grafo.vertices()) {
            if (p.getCapital().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Método usado para devolver um País através da sua Key no grafo
     *
     * @param key key pela qual o País é identificado
     * @return País(objecto)
     */
    public Pais getPaisByKey(int key) {
        for (Pais p : grafo.vertices()) {
            if (grafo.getKey(p) == key) {
                return p;
            }
        }
        return null;
    }

    /**
     * Método do algoritmo Welsh-Powell para colorir o mapa seguindo o teorema matemática das 4 cores
     */
    public void colorirMapa() {

        HashMap<Pais, Integer> mapaCores = new HashMap<>();
        Pais[] paises = grafo.allkeyVerts();
        ArrayList<Pais> arrayPaises = new ArrayList<>(Arrays.asList(paises));

        for (int i = 0; i < arrayPaises.size() - 1; i++) {
            int numeroFronteiras_i = grafo.outDegree(arrayPaises.get(i));
            for (int j = i; j < arrayPaises.size(); j++) {
                int numeroFronteiras_j = grafo.outDegree(arrayPaises.get(j));
                if (numeroFronteiras_i < numeroFronteiras_j) {
                    Pais pais_i = arrayPaises.get(i);
                    arrayPaises.set(i, arrayPaises.get(j));
                    arrayPaises.set(j, pais_i);
                }
            }
        }

        int cor = 0;
        while (arrayPaises.stream().anyMatch((pais) -> (!mapaCores.containsKey(pais)))) {
            for (Pais pais : arrayPaises) {
                if (!mapaCores.containsKey(pais)) {
                    boolean corDisponivel = true;
                    for (Pais fronteira : grafo.adjVertices(pais)) {
                        if (mapaCores.get(fronteira) != null) {
                            if (mapaCores.get(fronteira) == cor) {
                                corDisponivel = false;
                                break;
                            }
                        }
                    }
                    if (corDisponivel) {
                        mapaCores.put(pais, cor);
                    }
                }
            }
            cor++;
        }
        System.out.println("\n--------------------------------------\n\t    Mapa colorido\n--------------------------------------");
        System.out.println("Paleta: \nCor: 0 - Castanho\nCor: 1 - Verde Claro\nCor: 2 - Verde\nCor: 3 - Azul\n--------------------------------------");
        mapaCores.entrySet().forEach(entry -> {
            System.out.printf("País: %14s\t\tCor: %d\n", entry.getKey(), entry.getValue());
        });
        System.out.println("--------------------------------------\n");
    }

    /**
     * Método usado para devolver o caminho mais curto entre duas capitais, assim como a lista de capitais visitadas no processo
     *
     * @param orig Capital do país origem
     * @param dest Capital do país de destino
     * @return Objecto Par com a distância e uma lista dos países visitados no processo
     */
    public Pair<Double, LinkedList<Pais>> caminhoMaisCurto(Pais orig, Pais dest) {
        if (!grafo.validVertex(orig) || !grafo.validVertex(dest)) {
            return null;
        } else {
            LinkedList<Pais> shortPath = new LinkedList<>();
            double distancia = GraphAlgorithms.shortestPath(grafo, orig, dest, shortPath);
            Pair p = new Pair(distancia, shortPath);
            return p;
        }
    }

    /**
     * Método usado para devolver o caminho mais curto entre duas capitais bem como a distância entre as mesmas passando por outras capitais obrigatórias
     *
     * @param orig pais origem
     * @param dest pais de destino
     * @param paises lista de paises por onde passa
     * @return distância e caminho
     */
    public Pair<Double, ArrayList<Pais>> caminhoMaisCurtoComPaisesObrigatorios(Pais orig, Pais dest, ArrayList<Pais> paises) {
        if (!grafo.validVertex(orig) || !grafo.validVertex(dest)) {
            return null;
        }

        LinkedList<Pais> lstPaises = new LinkedList<>();

        if (GraphAlgorithms.shortestPath(grafo, orig, dest, lstPaises) == 0) {
            return null;
        }

        for (Pais p : paises) {
            if (!grafo.validVertex(p)) {
                return null;
            }

            if (GraphAlgorithms.shortestPath(grafo, orig, p, lstPaises) == 0) {
                return null;
            }
        }

        AdjacencyMatrix matrizAdjacencias = generateAdjacencyMatrix();

        ArrayList<Pais> ordem = new ArrayList<>();
        ordem.add(orig);
        for (Pais p : paises) {
            ordem.add(p);
        }
        ordem.add(dest);

        Pais paisOrigem = orig;
        int sizeArrayPaises = paises.size();
        Pais proximoPaisOrigem = paises.get(0);

        for (int i = 1; i < sizeArrayPaises; i++) {
            double distMaisCurta = matrizAdjacencias.getEdge(paisOrigem, ordem.get(i));
            for (int j = i; j < sizeArrayPaises; j++) {
                if (matrizAdjacencias.getEdge(paisOrigem, ordem.get(j + 1)) < distMaisCurta) {
                    distMaisCurta = matrizAdjacencias.getEdge(paisOrigem, ordem.get(j + 1));
                    proximoPaisOrigem = ordem.get(j + 1);
                }
            }
            if (paisOrigem != proximoPaisOrigem) {
                paisOrigem = proximoPaisOrigem;
                int indexPOrigem = ordem.indexOf(paisOrigem);
                ordem.set(indexPOrigem, ordem.get(i));
                ordem.set(i, paisOrigem);
            }
        }

        LinkedList<Pais> shortPath = new LinkedList<>();
        shortPath.add(orig);
        double distanciaFinal = 0;
        for (int k = 0; k < ordem.size() - 1; k++) {
            LinkedList<Pais> shortPathBetweenCountrys = new LinkedList<>();
            GraphAlgorithms.shortestPath(grafo, ordem.get(k), ordem.get(k + 1), shortPathBetweenCountrys);
            distanciaFinal += matrizAdjacencias.getEdge(ordem.get(k), ordem.get(k + 1));
            for (int z = 1; z < shortPathBetweenCountrys.size(); z++) {
                shortPath.add(shortPathBetweenCountrys.get(z));
            }
        }
        Pair p = new Pair(distanciaFinal, shortPath);
        return p;
    }

    /**
     * Método usado para devolver a matriz de adjacêcias do grafo
     *
     * @return matriz de adjacências
     */
    public AdjacencyMatrix generateAdjacencyMatrix() {

        AdjacencyMatrix matrizAdjacencias = new AdjacencyMatrix(grafo.numVertices());

        for (Pais p1 : grafo.vertices()) {
            matrizAdjacencias.insertVertex(p1);
        }

        for (Pais p1 : grafo.vertices()) {
            for (Pais p2 : grafo.vertices()) {
                if (grafo.getEdge(p1, p2) != null && !p1.equals(p2)) {
                    matrizAdjacencias.insertEdge(p1, p2, grafo.getEdge(p1, p2).getWeight());
                }
            }
        }
        matrizAdjacencias = MatrixAlgorithms.transitiveClosure(matrizAdjacencias, null);
        return matrizAdjacencias;
    }

    /**
     * Método usado para determinar o maior circuito que parte de uma capital origem e visita o maior número de outras capitais uma única vez, voltando à capital inicial usando a heuristica do vizinho mais próximo
     *
     * @param pais pais origem
     * @param paises lista de paises por onde passa
     * @return circuito efetuado
     */
    public LinkedList<Pais> maiorCircuito(Pais pais, LinkedList<Pais> paises) {

        paises.add(pais);

        if (!grafo.validVertex(pais)) {
            return null;
        }

        if (grafo.inDegree(pais) == 1 || grafo.inDegree(pais) == 0) {
            return paises;
        }
       
        if (grafo.inDegree(pais) == 2) {
            for(Pais p : grafo.adjVertices(pais)){
                if (grafo.inDegree(p) == 1) {
                    return paises;
                }
            }
        }

        Pais proximoPais;
        double distanciaMaisCurta;
        if (!(paises.contains(grafo.adjVertices(pais).iterator().next()))) {
            proximoPais = grafo.adjVertices(pais).iterator().next();
            distanciaMaisCurta = grafo.getEdge(pais, grafo.adjVertices(pais).iterator().next()).getWeight();
        } else {
            proximoPais = pais;
            distanciaMaisCurta = Double.MAX_VALUE;
        }

        for (Pais p : grafo.adjVertices(pais)) {
            if (grafo.getEdge(pais, p).getWeight() < distanciaMaisCurta) {
                if (!(paises.contains(p))) {
                    distanciaMaisCurta = grafo.getEdge(pais, p).getWeight();
                    proximoPais = p;
                }
            }
        }
        if (pais != proximoPais) {
            return maiorCircuito(proximoPais, paises);
        }

        LinkedList<Pais> paisesUsados = new LinkedList<Pais>();
        return devolveMaiorCircuito(pais, paises.get(0), paises, paisesUsados);
    }

    /**
     * Método usado para fazer backtracking para voltar ao país de origem
     *
     * @param pais último país visitado
     * @param paisOrigem país origem
     * @param paises lista de paises por onde passa
     * @param paisesUsados paises já visitados a partir dos quais não é possivel voltar ao pais de origem
     * @return circuito completo
     */
    public LinkedList<Pais> devolveMaiorCircuito(Pais pais, Pais paisOrigem, LinkedList<Pais> paises, LinkedList<Pais> paisesUsados) {

        Pais proximoPais;
        double distanciaMaisCurta;

        if (!(paises.contains(grafo.adjVertices(pais).iterator().next()))
                && !(paisesUsados.contains(grafo.adjVertices(pais).iterator().next()))) {
            proximoPais = grafo.adjVertices(pais).iterator().next();
            distanciaMaisCurta = grafo.getEdge(pais, grafo.adjVertices(pais).iterator().next()).getWeight();
        } else if (grafo.adjVertices(pais).iterator().next().equals(paisOrigem)) {
            paises.add(paisOrigem);
            return paises;
        } else {
            proximoPais = pais;
            distanciaMaisCurta = Double.MAX_VALUE;
        }

        for (Pais p : grafo.adjVertices(pais)) {
            if (grafo.getEdge(pais, p).getWeight() < distanciaMaisCurta) {
                if (!(paises.contains(p)) && !(paisesUsados.contains(p))) {
                    distanciaMaisCurta = grafo.getEdge(pais, p).getWeight();
                    proximoPais = p;
                } else if (p.equals(paisOrigem)) {
                    paises.add(paisOrigem);
                    return paises;
                }
            }
        }

        if (pais != proximoPais) {
            paises.add(proximoPais);
            return devolveMaiorCircuito(proximoPais, paisOrigem, paises, paisesUsados);
        } else {
            paises.remove(pais);
            paisesUsados.add(pais);
            return devolveMaiorCircuito(paises.get(paises.size() - 1), paisOrigem, paises, paisesUsados);
        }
    }
}
