package esinf_1st_project;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.exit;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.util.Pair;

/**
 *
 * @author Tiago Ribeiro
 * @author Bernardo Carvalho
 */
public class Fronteira {
    
    /**
     * variável Map de instância da classe central 'Fronteira'
     */
    public Map<Pais, Set<String>> mapFronteiras;
    
    /**
     * Construtor Fronteira
     */
    public Fronteira() {
        mapFronteiras = new HashMap<>();
    }

    /**
     * Método utilizado para ler os países de um ficheiro .txt e adicionar os objectos ao mapa
     * @param fileName nome do ficheiro txt
     */
    public void PaisReader(String fileName) {
        try {
            Scanner reader = new Scanner(new File(fileName));
            reader.useDelimiter(",|\n");

            Pais p = null;

            try {
                while (reader.hasNext()) {
                    String nome = reader.next().trim();
                    String continente = reader.next().trim();
                    String populacao = reader.next().trim();
                    String capital = reader.next().trim();
                    String latitude = reader.next().trim();
                    String longitude = reader.next().trim();

                    p = new Pais(nome, continente, Double.parseDouble(populacao), capital, Double.parseDouble(latitude), Double.parseDouble(longitude));
                    mapFronteiras.put(p, new HashSet<>());
                }
            } catch (NoSuchElementException e) {
                System.out.println("Erro na leitura do ficheiro Países. Por favor verifique o ficheiro '.txt' em causa.");
                e.printStackTrace();
                exit(0);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("O ficheiro de 'Países' especificado não existe. Por favor certifique-se da sua existência.");
            e.printStackTrace();
            exit(0);
        }
    }

    /**
     * Método utilizado para ler as fronteiras de um ficheiro .txt e adicionar as mesmas ao Set do respectivo país
     * @param fileName nome do ficheiro txt
     */
    public void FronteirasReader(String fileName) {
        if (mapFronteiras != null) {
            try {
                Scanner reader = new Scanner(new File(fileName));
                reader.useDelimiter(",|\n");

                try {
                    while (reader.hasNext()) {
                        String nomePais1 = reader.next().trim();
                        String nomePais2 = reader.next().trim();

                        Pais p1 = this.getPaisByNome(nomePais1);
                        Pais p2 = this.getPaisByNome(nomePais2);

                        Set<String> fronteirasP1 = mapFronteiras.get(p1);
                        fronteirasP1.add(nomePais2);

                        Set<String> fronteirasP2 = mapFronteiras.get(p2);
                        fronteirasP2.add(nomePais1);

                        mapFronteiras.put(p1, fronteirasP1);
                        mapFronteiras.put(p2, fronteirasP2);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Erro na leitura do ficheiro Fronteiras. Por favor verifique o ficheiro '.txt' em causa.");
                    e.printStackTrace();
                    exit(0);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("O ficheiro de 'Fronteiras' especificado não existe. Por favor certifique-se da sua existência.");
                e.printStackTrace();
                exit(0);
            }
        } else {
            System.out.println("Erro! Map das fronteiras está vazio.");
        }
    }

    /**
     * Método usado para devolver um País através do seu nome
     * @param name nome do País
     * @return País(objecto)
     */
    public Pais getPaisByNome(String name) {
        for (Pais p : mapFronteiras.keySet()) {
            if (p.getNome().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Método usado para devolver uma lista de países de um continente com mais de N milhões de habitantes.
     * O método chama o método 'sorting' implementado abaixo, para a ordenação.
     * @param continente continente desejado
     * @param N número de habitantes 
     * @return LinkedList os países com população maior que N habitantes
     */
    public LinkedList<Pair<Double, String>> paisesByPopulacao(String continente, int N) {
        LinkedList<Pair<Double, String>> lista = new LinkedList<>();
        for (Pais p : mapFronteiras.keySet()) {
            if (p.getContinente().equalsIgnoreCase(continente) && p.getPopulacao() > N) {
                Pair newPair = new Pair(p.getPopulacao(), p.getNome());
                lista.add(newPair);
            }
        }
        this.sorting(lista);
        return lista;
    }
    
    /**
     * Método usado para ordenar as keys (neste caso a população) por ordem crescente.
     * Recorrendo à implementação de uma classe anónima da interface Comparator com o critério de ordenação
     * @param lista recebe a paired linkedList para ordenar
     */
    public void sorting(LinkedList<Pair<Double, String>> lista) {
        Comparator<Pair<Double, String>> criterioDeSort = new Comparator<Pair<Double, String>>() {
            @Override
            public int compare(Pair<Double, String> par1, Pair<Double, String> par2) {
                return (int) (par1.getKey() - par2.getKey());
            }
        };
        Collections.sort(lista, criterioDeSort);
    }
    
    /**
     * Método usado para ordenar de forma decrescente o nº de fronteiras que cada país possui
     * @param mapFronteiras Map de Países(Objectos) assim como as suas fronteiras
     * @param continente Continente para agrupar países pelo mesmo
     * @return TreeMap com os países agrupados pelo mesmo número de países fronteiras
     */
    public TreeMap<Integer, HashSet<String>> numFronteirasPais(Map<Pais, Set<String>> mapFronteiras, String continente){
        TreeMap<Integer, HashSet<String>> lista = new TreeMap<>(Collections.reverseOrder());
        for(Pais pais : mapFronteiras.keySet()){
            if(pais.getContinente().equalsIgnoreCase(continente)){
                if(lista.keySet().contains(mapFronteiras.get(pais).size())){
                    lista.get(mapFronteiras.get(pais).size()).add(pais.getNome());
                }
                else{
                    HashSet<String> fronteiras = new HashSet<>();
                    fronteiras.add(pais.getNome());
                    lista.put(mapFronteiras.get(pais).size(), fronteiras);
                }
            }
        }
        return lista;
    }
    
    /**
     * Método utilizado para descobrir o número mínimo de fronteiras necessárias atravessar para ir de um país P1 a outro país P2.
     * @param p1 País origem
     * @param p2 País destino
     * @param count contagem iniciada a zero
     * @param map mapa que vai guardando os países por onde já passamos
     * @return numero de fronteiras atravessadas
     */
    public int minFronteiras(Pais p1, String p2, int count, TreeMap<Integer, TreeSet<String>> map){
        if(p1.getContinente().equalsIgnoreCase(this.getPaisByNome(p2).getContinente())){
            if(map.isEmpty()){
                count = 1;
                TreeSet<String> paisInicial = new TreeSet<>();
                paisInicial.add(p1.getNome());
                map.put(count, paisInicial);
            }
            else if(map.size() > 1 && map.get(map.lastKey()).equals(map.get(map.lowerKey(map.lastKey())))){
                return 0;
            }
            TreeSet<String> fronteiras = new TreeSet<>();
            for(String pais : map.get(count)){
                if(mapFronteiras.get(this.getPaisByNome(pais)).contains(p2)){
                    return count;
                }
                for(String fronteira : mapFronteiras.get(this.getPaisByNome(pais))){
                    fronteiras.add(fronteira);
                }
            }
            ++count;
            map.put(count, fronteiras);
            return minFronteiras(p1, p2, count, map);
        }
        else
            return 0;
    }
    
}
