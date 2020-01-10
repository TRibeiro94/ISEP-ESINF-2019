/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf_3rd_project;

import esinf_3rd_project.BST.Node;
import static java.lang.Math.pow;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author Bernardo Carvalho
 * @author Tiago Ribeiro
 */
public class Mapa {
    
    public Reader_Manager reader;
    public HashMap<String, Pais> mapa_paises_nome;
    public AVL<Pais> arvore_nome;
    public HashMap<String, Pais> mapa_paises;
    public AVL<Pais> arvore_nFronteiras_populacao;
    public Kd_tree arvore_2d;

    /**
     * Construtor da classe central mapa
     */
    public Mapa() {
        this.reader = new Reader_Manager(this);
        this.mapa_paises_nome = new HashMap();
        this.arvore_nome = new AVL<>();
        this.mapa_paises = new HashMap();
        this.arvore_nFronteiras_populacao = new AVL<>();
        this.arvore_2d = new Kd_tree();
    }
    
    /**
     * Método usado para devolver um País através do seu nome numa árvore AVL construida com base no nome do país
     *
     * @param name nome do País
     * @return País(objecto)
     */
    public Pais_Comparable_Nome getPaisByNomeArvore_Nome(String name) {
        Pais_Comparable_Nome p = new Pais_Comparable_Nome(name);
        Node<Pais> node = arvore_nome.find(p, arvore_nome.root());
        return (Pais_Comparable_Nome)node.getElement();
    }
    
    /**
     * Método usado para adicionar uma fronteira a um Pais e incremntar o seu número de fronteiras numa árvore AVL construida com base no nome do país
     * @param pais1 nome do país ao qual vai ser adicionado uma fronteira
     * @param pais2 nome da fronteira a ser adicionada
     */
    public void addFronteiraArvore_Nome(String pais1, String pais2){
        if(!(pais1.equalsIgnoreCase(pais2))){
            Pais p = mapa_paises_nome.get(pais1);
            if(!(p.getFronteiras().contains(pais2))){
                LinkedList<String> fronteiras = p.getFronteiras();
                fronteiras.add(pais2);
                p.setFronteiras(fronteiras);
                int numFronteiras = p.getNumFronteiras();
                numFronteiras++;
                p.setNumFronteiras(numFronteiras);
            }
        }
    }
    
    /**
     * Método usado para adicionar uma fronteira a um Pais e incremntar o seu número de fronteiras
     * @param pais1 nome do país ao qual vai ser adicionado uma fronteira
     * @param pais2 nome da fronteira a ser adicionada
     */
    public void addFronteiraMapaPaises(String pais1, String pais2){
        if(!(pais1.equalsIgnoreCase(pais2))){
            Pais p = mapa_paises.get(pais1);
                if(!(p.getFronteiras().contains(pais2))){
                    LinkedList<String> fronteiras = p.getFronteiras();
                    fronteiras.add(pais2);
                    p.setFronteiras(fronteiras);
                    int numFronteiras = p.getNumFronteiras();
                    numFronteiras++;
                    p.setNumFronteiras(numFronteiras);
                }
        }
    }
    
    /**
     * Método usado para adicionar uma lista de paises e deste modo criar uma árvore AVL com base no nome de cada país
     */
    public void insertIntoArvore_Nome(){
        for(Pais p : this.mapa_paises_nome.values()){
            arvore_nome.insert(p);
        }
    }
    
    /**
     * Método usado para adicionar uma lista de paises e deste modo criar uma árvore AVL com base no número de fronteiras e população de cada país
     */
    public void insertIntoArvore_NFronteiras_Populacao(){
        for(Pais p : this.mapa_paises.values()){
            arvore_nFronteiras_populacao.insert(p);
        }
    }
    
    /**
     * Método usado para adicionar uma lista de paises e deste modo criar uma árvore KD com base na latitude para níveis pares e longitude para níveis impares
     */
    public void insertIntoArvore_KD(){
        for(Pais p : this.mapa_paises.values()){
            arvore_2d.insert(p);
        }
    }
    
    /**
     * Método usado para devolver a lista das fronteiras de um determinado país cujo nome é passado por parâmetro
     * @param pais nome do país
     * @return lista das fronteiras do pais passado por parâmetro
     */
    public LinkedList<String> listaFronteiras(String pais){
        if(this.getPaisByNomeArvore_Nome(pais) != null){
            return this.getPaisByNomeArvore_Nome(pais).getFronteiras();
        }
        return null;
    }
    
    /**
     * Método usado para devolver uma lista ordenada dos países pertencentes a um determinado continente. 
     * A ordenação é efetuada por ordem decrescente por número de fronteiras e crescente por valor de população.
     * @param continente nome do continente ao qual os paises da lista ordenada têm de pertencer
     * @return lista ordenada de paises pertencentes a um determinado continente
     */
    public LinkedList<Pais> listaOrdenadaContinente(String continente){
        LinkedList<Pais> paises = new LinkedList<>();
        
        for(Pais p : arvore_nFronteiras_populacao.inOrder()){
            if(p.getContinente().equalsIgnoreCase(continente)){
                paises.add(p);
            }
        }
        
        return paises;
    }
    
    /**
     * Método usado para devolver um país cujas coordenadas da capital são passadas por parâmetro numa árvore KD
     * @param latitude latitude da capital do país
     * @param longitude longitude da capital do país
     * @return país
     */
    public Pais getPaisByCoordenadas(double latitude, double longitude){
        Pais_Comparable_NFronteiras_Populacao p = new Pais_Comparable_NFronteiras_Populacao(latitude, longitude);
        Node<Pais> node = arvore_2d.find(p, arvore_2d.root());
        return node.getElement();
    }
    
    /**
     * Método usado para encontrar o país cuja capital está mais próxima das coordenadas indicadas
     * @param latitude coordenada da latitude
     * @param longitude coordenada da longitude
     * @return país cuja capital se encontra mais próxima das coordenadas indicadas
     */
    public Pais vizinhoMaisProximo(double latitude, double longitude){
        
        if(arvore_2d.root() == null){
            return null;
        }
        
        if(arvore_2d.root().getElement().getLatitude() == latitude && arvore_2d.root().getElement().getLongitude() == longitude){
            return arvore_2d.root().getElement();
        }
        
        return getVizinhoMaisProximo(latitude, longitude, arvore_2d.root(), Double.MAX_VALUE, arvore_2d.root().getElement(), false);
    }
    
    /**
     * Método recursivo usado para encontrar o país cuja capital está mais próxima das coordenadas indicadas
     * @param latitude coordenada da latitude
     * @param longitude coordenada da longitude
     * @param pais node do tipo Pais que inica o país atual em que nos encontramos na tree
     * @param distancia valor da menor distância às coordenadas indicadas
     * @param paisMaisProximo Pais mais próximo das coordenadas indicadas
     * @param leafFound valor booleano que indica se uma leaf foi encontrada ou não
     * @return país cuja capital se encontra mais próxima das coordenadas indicadas
     */
    public Pais getVizinhoMaisProximo(double latitude, double longitude, Node<Pais> pais, double distancia, Pais paisMaisProximo, boolean leafFound){
        
        double menorDistancia = distancia;
        
        if(leafFound == false){
            if(pais.getLeft() == null && pais.getRight() == null){
                if(arvore_2d.parentNode(pais) != null){
                    distancia = this.calculoDistancia(latitude, longitude, pais);
                    if(distancia < menorDistancia){
                        menorDistancia = distancia;
                        return getVizinhoMaisProximo(latitude, longitude, arvore_2d.parentNode(pais), menorDistancia, pais.getElement(), true);
                    }
                    else{
                        return getVizinhoMaisProximo(latitude, longitude, arvore_2d.parentNode(pais), menorDistancia, paisMaisProximo, true);
                    }
                }
                else{
                    return paisMaisProximo;
                }
            }
            else{
                if((arvore_2d.getLevel(pais.getElement()) % 2) != 0){
                    if (latitude < pais.getElement().getLatitude() && pais.getLeft() != null) {
                        return getVizinhoMaisProximo(latitude, longitude, pais.getLeft(), menorDistancia, paisMaisProximo, false);
                    } else {
                        return getVizinhoMaisProximo(latitude, longitude, pais.getRight(), menorDistancia, paisMaisProximo, false);
                    }
                }
                else{
                    if (longitude < pais.getElement().getLongitude() && pais.getLeft() != null) {
                        return getVizinhoMaisProximo(latitude, longitude, pais.getLeft(), menorDistancia, paisMaisProximo, false);
                    } else {
                        return getVizinhoMaisProximo(latitude, longitude, pais.getRight(), menorDistancia, paisMaisProximo, false);
                    }
                }
            }
        }
        else{
            distancia = this.calculoDistancia(latitude, longitude, pais);
            if(distancia < menorDistancia){
                menorDistancia = distancia;
                if(pais.getLeft() != null){
                    if(this.calculoDistancia(latitude, longitude, pais.getLeft()) < menorDistancia){
                        return getVizinhoMaisProximo(latitude, longitude, pais.getLeft(), this.calculoDistancia(latitude, longitude, pais.getLeft()), pais.getLeft().getElement(), false);
                    }
                }
                if(pais.getRight() != null){
                    if(this.calculoDistancia(latitude, longitude, pais.getRight()) < menorDistancia){
                        return getVizinhoMaisProximo(latitude, longitude, pais.getRight(), this.calculoDistancia(latitude, longitude, pais.getRight()), pais.getRight().getElement(), false);
                    }
                }
                if(arvore_2d.parentNode(pais) != null){
                    return getVizinhoMaisProximo(latitude, longitude, arvore_2d.parentNode(pais), menorDistancia, pais.getElement(), true);
                }
                else{
                    return pais.getElement();
                }
            }
            else{
                if(arvore_2d.parentNode(pais) != null){
                    return getVizinhoMaisProximo(latitude, longitude, arvore_2d.parentNode(pais), menorDistancia, paisMaisProximo, true);
                }
                else{
                    return paisMaisProximo;
                }
            }
        }
    }
    
    /**
     * Método usado para calcular a distância entre as coordenadas de um ponto e as coordenadas da capital de um país
     * @param latitude coordenada da latitude
     * @param longitude coordenada da longitude
     * @param pais instância de país a partir do qual se calcula a distância ao ponto cujas coordenadas são indicadas
     * @return distância entre a capital de um país contido na árvore e um ponto de coordenadas (latitude, longitude)
     */
    public double calculoDistancia(double latitude, double longitude, Node<Pais> pais){
        
        double distancia = pow((latitude - pais.getElement().getLatitude()), 2) + pow((longitude - pais.getElement().getLongitude()), 2);
        
        return distancia;
    }
    
    /**
     * Método usado para devolver uma lista de paises contidos numa área rectangular definida por dois pontos (latitude1, longitude1), (latitude2, longitude2)
     * onde (latitude1, longitude1) é o canto superior esquerdo do rectângulo e (latitude2, longitude2) é o canto inferior direito do rectângulo
     * @param latitude1 coordenada da latitude do primeiro ponto
     * @param longitude1 coordenada da longitude do primeiro ponto
     * @param latitude2 coordenada da latitude do segundo ponto
     * @param longitude2 coordenada da longitude do segundo ponto
     * @return lista de paises contidos na área geográfica
     */
    public HashSet<Pais> pesquisaAreaGeografica(double latitude1, double longitude1, double latitude2, double longitude2){
        
        HashSet<Pais> paisesContidos = new HashSet<>();
        
        if(arvore_2d.isEmpty() || arvore_2d.root() == null){
            return paisesContidos;
        }
        
        return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, arvore_2d.root(), paisesContidos, new HashSet<>());
    }
    
    /**
     * Método usado para devolver uma lista de paises contidos numa área rectangular definida por dois pontos (latitude1, longitude1), (latitude2, longitude2)
     * onde (latitude1, longitude1) é o canto superior esquerdo do rectângulo e (latitude2, longitude2) é o canto inferior direito do rectângulo,
     * pais, é o node do tipo Pais que está a ser verificado, paisesContidos é a lista de paises que estão contidos na área deliniada pelos dois pontos,
     * e nodesVisitados é a lista de nodes do tipo Pais por onde o método já passou, tendo verificado se os paises desses nodes estão ou não contidos na área rectangular.
     * @param latitude1 coordenada da latitude do primeiro ponto
     * @param longitude1 coordenada da longitude do primeiro ponto
     * @param latitude2 coordenada da latitude do segundo ponto
     * @param longitude2 coordenada da longitude do segundo ponto
     * @param pais node do tipo Pais que indica o pais que vai ser visitado para se verificar se este está ou não contido na área
     * @param paisesContidos lista de paises contidos na área geográfica
     * @param nodesVisitados lista de nodes do tipo Pais que o método já visitou
     * @return lista de paises contidos na área geográfica
     */
    public HashSet<Pais> pesquisaAreaGeografica(double latitude1, double longitude1, double latitude2, double longitude2, Node<Pais> pais, HashSet<Pais> paisesContidos, HashSet<Node<Pais>> nodesVisitados){
        
        nodesVisitados.add(pais);
        
        if((pais.getLeft() == null && pais.getRight() == null) || (nodesVisitados.contains(pais.getLeft()) && nodesVisitados.contains(pais.getRight()))){
            if(pais.getElement().getLatitude() <= latitude1 && pais.getElement().getLatitude() >= latitude2){
                if(pais.getElement().getLongitude() >= longitude1 && pais.getElement().getLongitude() <= longitude2){
                        paisesContidos.add(pais.getElement());
                }
            }
            if(arvore_2d.parentNode(pais) != null){
                return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, arvore_2d.parentNode(pais), paisesContidos, nodesVisitados);
            }
            else{
                return paisesContidos;
            }
        }
        
        if((arvore_2d.getLevel(pais.getElement()) % 2) != 0){
            if(pais.getElement().getLatitude() <= latitude1 && pais.getElement().getLatitude() >= latitude2){
                if(pais.getElement().getLongitude() >= longitude1 && pais.getElement().getLongitude() <= longitude2){
                        paisesContidos.add(pais.getElement());
                }
                if(pais.getLeft() != null && !nodesVisitados.contains(pais.getLeft())){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, pais.getLeft(), paisesContidos, nodesVisitados);
                }
                else if(pais.getRight() != null && !nodesVisitados.contains(pais.getRight())){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, pais.getRight(), paisesContidos, nodesVisitados);
                }
                else if(arvore_2d.parentNode(pais) != null){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, arvore_2d.parentNode(pais), paisesContidos, nodesVisitados);
                }
                else{
                    return paisesContidos;
                }
            }
            if(pais.getElement().getLatitude() > latitude1){
                if(pais.getRight() != null){
                    nodesVisitados.add(pais.getRight());
                }
                if(pais.getLeft() != null && !nodesVisitados.contains(pais.getLeft())){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, pais.getLeft(), paisesContidos, nodesVisitados);
                }
                else if(arvore_2d.parentNode(pais) != null){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, arvore_2d.parentNode(pais), paisesContidos, nodesVisitados);
                }
                else{
                    return paisesContidos;
                }
            }
            else{
                if(pais.getLeft() != null){
                    nodesVisitados.add(pais.getLeft());
                }
                if(pais.getRight() != null && !nodesVisitados.contains(pais.getRight())){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, pais.getRight(), paisesContidos, nodesVisitados);
                }
                else if(arvore_2d.parentNode(pais) != null){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, arvore_2d.parentNode(pais), paisesContidos, nodesVisitados);
                }
                else{
                    return paisesContidos;
                }
            }
        }
        else{
            if(pais.getElement().getLongitude() >= longitude1 && pais.getElement().getLongitude() <= longitude2){
                if(pais.getElement().getLatitude() <= latitude1 && pais.getElement().getLatitude() >= latitude2){
                        paisesContidos.add(pais.getElement());
                }
                if(pais.getLeft() != null && !nodesVisitados.contains(pais.getLeft())){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, pais.getLeft(), paisesContidos, nodesVisitados);
                }
                else if(pais.getRight() != null && !nodesVisitados.contains(pais.getRight())){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, pais.getRight(), paisesContidos, nodesVisitados);
                }
                else if(arvore_2d.parentNode(pais) != null){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, arvore_2d.parentNode(pais), paisesContidos, nodesVisitados);
                }
                else{
                    return paisesContidos;
                }
            }
            if(pais.getElement().getLongitude() < longitude1){
                if(pais.getLeft() != null){
                    nodesVisitados.add(pais.getLeft());
                }
                if(pais.getRight() != null && !nodesVisitados.contains(pais.getRight())){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, pais.getRight(), paisesContidos, nodesVisitados);
                }
                else if(arvore_2d.parentNode(pais) != null){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, arvore_2d.parentNode(pais), paisesContidos, nodesVisitados);
                }
                else{
                    return paisesContidos;
                }
            }
            else{
                if(pais.getRight() != null){
                    nodesVisitados.add(pais.getRight());
                }
                if(pais.getLeft() != null && !nodesVisitados.contains(pais.getLeft())){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, pais.getLeft(), paisesContidos, nodesVisitados);
                }
                else if(arvore_2d.parentNode(pais) != null){
                    return pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2, arvore_2d.parentNode(pais), paisesContidos, nodesVisitados);
                }
                else{
                    return paisesContidos;
                }
            }
        }
    }
}
