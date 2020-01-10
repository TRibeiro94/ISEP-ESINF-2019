package esinf_1st_project;

import esinf_1st_project.Pais;
import esinf_1st_project.Fronteira;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Ribeiro
 * @author Bernardo Carvalho
 */
public class FronteiraTest {
    
    public FronteiraTest() {
    }

    /**
     * Test of PaisReader method, of class Fronteira.
     */
    @Test
    public void testPaisReader() {
        System.out.println("\nTeste ao método: PaisReader");
        Fronteira instance = new Fronteira();
        
        String fileName = "paises.txt";
        instance.PaisReader(fileName);
        int paisesLidos = instance.mapFronteiras.size();
        System.out.println("Número de países no ficheiro = 59\n");
        int numeroPaisesNoFicheiro = 59;
        assertEquals(paisesLidos, numeroPaisesNoFicheiro);
    }

    /**
     * Test of FronteirasReader method, of class Fronteira.
     */
    @Test
    public void testFronteirasReader() {
        System.out.println("\nTeste ao método: FronteirasReader");
        Fronteira instance = new Fronteira();
        instance.PaisReader("paises.txt");
        instance.FronteirasReader("fronteiras.txt");
        Pais p1 = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        boolean resultado = instance.mapFronteiras.containsKey(p1);
        boolean expResult = true;
        assertEquals(resultado, expResult);
        System.out.println("Impressão do HashMap com todas as Keys e respectivos Values");
        instance.mapFronteiras.entrySet().forEach(entry->{
             System.out.println(entry.getKey() + "  \nValues: " + entry.getValue());  
         });
     }

    /**
     * Test of getPaisByNome method, of class Fronteira.
     */
    @Test
    public void testGetPaisByNome() {
        System.out.println("\nTeste ao método: getPaisByNome (nome nao existente)");
        String name = "china";
        Fronteira instance = new Fronteira();
        instance.PaisReader("paises.txt");
        instance.FronteirasReader("fronteiras.txt");
        Pais expResult = null;
        Pais result = instance.getPaisByNome(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPaisByNome method, of class Fronteira.
     */
    @Test
    public void testGetPaisByNome2() {
        System.out.println("\nTeste ao método: getPaisByNome (nome existente)");
        String name = "portugal";
        Fronteira instance = new Fronteira();
        instance.PaisReader("paises.txt");
        instance.FronteirasReader("fronteiras.txt");
        Pais p1 = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Pais expResult = p1;
        Pais result = instance.getPaisByNome(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of paisesByPopulacao method, of class Fronteira.
     */
    @Test
    public void testPaisesByPopulacao() {
        System.out.println("\nTeste ao método: paisesByPopulacao");
        String continente = "americasul";
        int N = 40;
        
        Fronteira instance = new Fronteira();
        instance.PaisReader("paises.txt");
        instance.FronteirasReader("fronteiras.txt");
        Map<Pais, Set<String>> mapFronteiras = new HashMap<>();
        
        Pais p1 = new Pais("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Pais p3 = new Pais("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        Pais p2 = new Pais("colombia", "americasul", 46.86, "bogota",  4.6097100, -74.0817500);
        Pair newPair1 = new Pair(p1.getPopulacao(), p1.getNome());
        Pair newPair2 = new Pair(p3.getPopulacao(), p3.getNome());
        Pair newPair3 = new Pair(p2.getPopulacao(), p2.getNome());
        
        mapFronteiras.put(p1, new HashSet<>());
        mapFronteiras.put(p3, new HashSet<>());
        mapFronteiras.put(p2, new HashSet<>());
        LinkedList<Pair<Double, String>> expResult = new LinkedList<>();
        
        expResult.add(newPair1);
        expResult.add(newPair3);
        expResult.add(newPair2);
        
        LinkedList<Pair<Double, String>> result = instance.paisesByPopulacao(continente, N);
        assertEquals(expResult, result);
        
        System.out.println("\nPrint para teste (Continente Europa, N = 50): ");
        System.out.println(instance.paisesByPopulacao("europa", 50) + "\n");
    }

    /**
     * Test of sorting method, of class Fronteira.
     */
    @Test
    public void testSorting() {
        System.out.println("Teste ao método: sorting");
        Pair p1 = new Pair(82.8,"alemanha");
        Pair p2 = new Pair(3.01,"armenia");
        Pair p3 = new Pair(8.77,"austria");
        Pair p4 = new Pair(11.37,"belgica");
        Pair p5 = new Pair(3.75,"bosnia");
        
        LinkedList<Pair<Double, String>> lista = new LinkedList<>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        lista.add(p4);
        lista.add(p5);
        
        LinkedList<Pair<Double, String>> expResult = new LinkedList<>();
        expResult.addFirst(p2);
        expResult.addLast(p5);
        expResult.addLast(p3);
        expResult.addLast(p4);
        expResult.addLast(p1);
        
        Fronteira instance = new Fronteira();
        instance.sorting(lista);
        assertEquals(lista, expResult);
    }

    /**
     * Test of numFronteirasPais method, of class Fronteira.
     */
    @Test
    public void testNumFronteirasPais() {
        System.out.println("Teste ao método: numFronteirasPais");
        Fronteira instance = new Fronteira();
        instance.PaisReader("paises.txt");
        instance.FronteirasReader("fronteiras.txt");
        String continente = "americasul";
        TreeMap<Integer, HashSet<String>> expResult = new TreeMap<>(Collections.reverseOrder());
        HashSet<String> h1 = new HashSet<>(Arrays.asList("guianafrancesa", "equador", "uruguai"));
        HashSet<String> h2 = new HashSet<>(Arrays.asList("paraguai", "suriname", "guiana", "chile", "venezuela"));
        HashSet<String> h3 = new HashSet<>(Arrays.asList("colombia"));
        HashSet<String> h4 = new HashSet<>(Arrays.asList("argentina", "bolivia", "peru"));
        HashSet<String> h5 = new HashSet<>(Arrays.asList("brasil"));

        expResult.put(2, h1);
        expResult.put(3, h2);
        expResult.put(4, h3);
        expResult.put(5, h4);
        expResult.put(10, h5);
        
        TreeMap<Integer, HashSet<String>> result = instance.numFronteirasPais(instance.mapFronteiras, continente);
        assertEquals(expResult, result);
        System.out.println("\nPrint para teste: (Continente: America Sul)");
        System.out.println(instance.numFronteirasPais(instance.mapFronteiras, continente)+"\n");
    }

    /**
     * Test of minFronteiras method, of class Fronteira.
     */
    @Test
    public void testMinFronteiras() {
        System.out.println("minFronteiras");
        Pais p1 = new Pais("eslovaquia", "europa", 5.44, "bratislava", 48.1483765, 17.1073105);
        String p2 = "grecia";
        int count = 0;
        TreeMap<Integer, TreeSet<String>> map = new TreeMap<>();
        Fronteira instance = new Fronteira();
        instance.PaisReader("paises.txt");
        instance.FronteirasReader("fronteiras.txt");
        int expResult = 4;
        int result = instance.minFronteiras(p1, p2, count, map);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of minFronteiras method, of class Fronteira.
     */
    @Test
    public void testMinFronteiras2() {
        System.out.println("minFronteiras");
        Pais p1 = new Pais("eslovaquia", "europa", 5.44, "bratislava", 48.1483765, 17.1073105);
        String p2 = "brasil";
        int count = 0;
        TreeMap<Integer, TreeSet<String>> map = new TreeMap<>();
        Fronteira instance = new Fronteira();
        instance.PaisReader("paises.txt");
        instance.FronteirasReader("fronteiras.txt");
        int expResult = 0;
        int result = instance.minFronteiras(p1, p2, count, map);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of minFronteiras method, of class Fronteira.
     */
    @Test
    public void testMinFronteiras3() {
        System.out.println("minFronteiras");
        Pais p1 = new Pais("eslovaquia", "europa", 5.44, "bratislava", 48.1483765, 17.1073105);
        String p2 = "reinounido";
        int count = 0;
        TreeMap<Integer, TreeSet<String>> map = new TreeMap<>();
        Fronteira instance = new Fronteira();
        instance.PaisReader("paises.txt");
        instance.FronteirasReader("fronteiras.txt");
        int expResult = 0;
        int result = instance.minFronteiras(p1, p2, count, map);
        assertEquals(expResult, result);
    }
}