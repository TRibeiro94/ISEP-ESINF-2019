/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf_3rd_project;

import esinf_3rd_project.Pais_Comparable_Nome;
import esinf_3rd_project.Mapa;
import esinf_3rd_project.Pais_Comparable_NFronteiras_Populacao;
import esinf_3rd_project.Pais;
import esinf_3rd_project.BST.Node;
import java.util.HashSet;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Ribeiro
 * @author Bernardo Carvalho
 */
public class MapaTest {

    public MapaTest() {
    }

    /**
     * Test of getPaisByNomeArvore method, of class Mapa.
     */
    @Test
    public void testGetPaisByNomeArvore() {
        System.out.println("Teste: getPaisByNomeArvore");
        String name1 = "malta";
        String name2 = "servia";
        String name3 = "irlanda";

        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");

        Pais expResult = new Pais_Comparable_Nome("malta", "europa", 0.44, "valletta", 35.904171, 14.518907);
        Pais expResult2 = new Pais_Comparable_Nome("servia", "europa", 7.04, "belgrado", 44.802416, 20.465601);
        Pais expResult3 = new Pais_Comparable_Nome("irlanda", "europa", 4.77, "dublin", 53.344104, -6.2674937);
        Pais result = instance.getPaisByNomeArvore_Nome(name1);
        Pais result2 = instance.getPaisByNomeArvore_Nome(name2);
        Pais result3 = instance.getPaisByNomeArvore_Nome(name3);

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);

        System.out.println("\n");
    }

    /**
     * Test of listaFronteiras method, of class Mapa.
     */
    @Test
    public void testListaFronteiras() {
        System.out.println("-----* EXERCÍCIO 1 a) *-----");
        System.out.println("\nTeste: listaFronteiras");
        System.out.println("|Dado um nome de um país, devolve a lista das suas fronteiras|");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");

        String pais = "espanha";
        String pais2 = "kosovo";
        String pais3 = "alemanha"; //[austria, belgica, dinamarca, franca, holanda, luxemburgo, polonia, republicacheca, suica]

        LinkedList<String> expResult = new LinkedList<>();
        expResult.add("franca");
        expResult.add("portugal");
        LinkedList<String> result = instance.listaFronteiras(pais);

        LinkedList<String> expResult2 = new LinkedList<>();
        expResult2.add("albania");
        expResult2.add("macedonia");
        expResult2.add("montenegro");
        expResult2.add("servia");
        LinkedList<String> result2 = instance.listaFronteiras(pais2);

        LinkedList<String> expResult3 = new LinkedList<>();
        expResult3.add("austria");
        expResult3.add("belgica");
        expResult3.add("dinamarca");
        expResult3.add("franca");
        expResult3.add("holanda");
        expResult3.add("luxemburgo");
        expResult3.add("polonia");
        expResult3.add("republicacheca");
        expResult3.add("suica");
        LinkedList<String> result3 = instance.listaFronteiras(pais3);

        System.out.println("\nInput : Espanha");
        System.out.println("Output: " + result);
        System.out.println("\nInput : Kosovo");
        System.out.println("Output: " + result2);
        System.out.println("\nInput : Alemanha");
        System.out.println("Output: " + result3);

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        System.out.println("\n------------------------------------------------------------\n");
    }

    /**
     * Test of listaOrdenadaContinente method, of class Mapa.
     */
    @Test
    public void testListaOrdenadaContinente() {
        System.out.println("-----* EXERCÍCIO 1 b) *-----");
        System.out.println("1º Teste: listaOrdenadaContinente\n");
        System.out.println("\t\t       AMÉRICA DO SUL\n------------------------------------------------------------");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        String continente = "americasul";

        String expResult1 = instance.getPaisByNomeArvore_Nome("brasil").getNome();
        String result1 = instance.listaOrdenadaContinente(continente).get(0).getNome();
        String expResult2 = instance.getPaisByNomeArvore_Nome("colombia").getNome();
        String result2 = instance.listaOrdenadaContinente(continente).get(4).getNome();
        String expResult3 = instance.getPaisByNomeArvore_Nome("equador").getNome();
        String result3 = instance.listaOrdenadaContinente(continente).get(12).getNome();

        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);

        LinkedList<Pais> listaOrdenada = instance.listaOrdenadaContinente(continente);
        for (Pais p : listaOrdenada) {
            System.out.printf("País: %14s | Fronteiras: %3d | População: %6s |%n", p.getNome(), p.getNumFronteiras(), p.getPopulacao());
        }
        System.out.println("\n------------------------------------------------------------\n");
    }

    /**
     * Test of listaOrdenadaContinente method, of class Mapa.
     */
    @Test
    public void testListaOrdenadaContinente2() {
        System.out.println("-----* EXERCÍCIO 1 b) *-----");
        System.out.println("2º Teste: listaOrdenadaContinente\n");
        System.out.println("\t\t\t EUROPA\n------------------------------------------------------------");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        String continente = "europa";

        String expResult1 = instance.getPaisByNomeArvore_Nome("alemanha").getNome();
        String result1 = instance.listaOrdenadaContinente(continente).get(0).getNome();
        String expResult2 = instance.getPaisByNomeArvore_Nome("croacia").getNome();
        String result2 = instance.listaOrdenadaContinente(continente).get(10).getNome();
        String expResult3 = instance.getPaisByNomeArvore_Nome("luxemburgo").getNome();
        String result3 = instance.listaOrdenadaContinente(continente).get(26).getNome();

        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);

        LinkedList<Pais> listaOrdenada = instance.listaOrdenadaContinente(continente);
        for (Pais p : listaOrdenada) {
            System.out.printf("País: %14s | Fronteiras: %3d | População: %6s |%n", p.getNome(), p.getNumFronteiras(), p.getPopulacao());
        }
        System.out.println("\n------------------------------------------------------------\n");
    }

    /**
     * Test of listaOrdenadaContinente method, of class Mapa.
     */
    @Test
    public void testListaOrdenadaContinente3() {
        System.out.println("-----* EXERCÍCIO 1 b) *-----");
        System.out.println("3º Teste: listaOrdenadaContinente\n");
        System.out.println("Input: Ásia");
        System.out.println("Output: 'null', pois não existe nenhum país da Ásia.");
        System.out.println("\n------------------------------------------------------------\n");

        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        String continente = "asia";

        LinkedList<Pais> expResult = new LinkedList<>();
        LinkedList<Pais> result = instance.listaOrdenadaContinente(continente);

        assertEquals(expResult, result);
    }

    /**
     * Test of getPaisByNomeArvore_Nome method, of class Mapa.
     */
    @Test
    public void testGetPaisByNomeArvore_Nome() {
        System.out.println("Teste: getPaisByNomeArvore_Nome");

        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");

        String name = "espanha";
        Pais_Comparable_Nome p = new Pais_Comparable_Nome("espanha", "europa", 46.53, "madrid", 40.4166909, -3.7003454);

        Pais_Comparable_Nome expResult = p;
        Pais_Comparable_Nome result = instance.getPaisByNomeArvore_Nome(name);
        assertEquals(expResult, result);
        System.out.println("\n");
    }

    /**
     * Test of addFronteiraArvore_Nome method, of class Mapa.
     */
    @Test
    public void testAddFronteiraArvore_Nome() {
        System.out.println("Teste: addFronteiraArvore_Nome");

        String pais1 = "portugal";
        String pais2 = "espanha";

        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.addFronteiraArvore_Nome(pais1, pais2);
        instance.insertIntoArvore_Nome();

        LinkedList<String> expResult = new LinkedList<>();
        expResult.add("espanha");
        LinkedList<String> result = instance.getPaisByNomeArvore_Nome(pais1).getFronteiras();

        assertEquals(expResult, result);
        System.out.println("\n");
    }

    /**
     * Test of insertIntoArvore_NFronteiras_Populacao method, of class Mapa.
     */
    @Test
    public void testInsertIntoArvore_NFronteiras_Populacao() {
        System.out.println("Teste: insertIntoArvore_NFronteiras_Populacao");
        Mapa instance = new Mapa();

        LinkedList<Pais> expResult = new LinkedList<>();
        Pais p1 = new Pais_Comparable_Nome("espanha", "europa", 46.53, "madrid", 40.4166909, -3.7003454);
        Pais p2 = new Pais_Comparable_Nome("franca", "europa", 66.99, "paris", 48.8566667, 2.3509871);
        Pais p3 = new Pais_Comparable_Nome("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);

        instance.mapa_paises.put(p1.getNome(), p1);
        instance.mapa_paises.put(p2.getNome(), p2);
        instance.mapa_paises.put(p3.getNome(), p3);
        instance.insertIntoArvore_NFronteiras_Populacao();

        LinkedList<Pais> result = new LinkedList<>();
        for (Pais p : instance.arvore_nFronteiras_populacao.inOrder()) {
            result.add(p);
        }
        assertEquals(expResult, result);
        System.out.println("\n");
    }

    /**
     * Test of addFronteiraMapaPaises method, of class Mapa.
     */
    @Test
    public void testAddFronteiraMapaPaises() {
        System.out.println("addFronteiraMapaPaises");

        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        Pais p1 = new Pais_Comparable_Nome("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Pais p2 = new Pais_Comparable_Nome("espanha", "europa", 46.53, "madrid", 40.4166909, -3.7003454);

        instance.mapa_paises.put(p1.getNome(), p1);
        instance.addFronteiraMapaPaises(p1.getNome(), p2.getNome());

        LinkedList<String> result = p1.getFronteiras();

        LinkedList<String> expResult = new LinkedList<>();
        expResult.add("espanha");

        assertEquals(expResult, result);
        System.out.println("\n");
    }

    /**
     * Test of insertIntoArvore_Nome method, of class Mapa.
     */
    @Test
    public void testInsertIntoArvore_Nome() {
        System.out.println("Teste: insertIntoArvore_Nome");

        Mapa instance = new Mapa();
        Pais p1 = new Pais_Comparable_Nome("alemanha", "europa", 82.8, "berlim", 52.5234051, 13.4113999);
        Pais p2 = new Pais_Comparable_Nome("bosnia", "europa", 3.75, "sarajevo", 43.85643, 18.41342);
        Pais p3 = new Pais_Comparable_Nome("eslovaquia", "europa", 5.44, "bratislava", 48.1483765, 17.1073105);
        Pais p4 = new Pais_Comparable_Nome("montenegro", "europa", 0.62, "podgorica", 42.442575, 19.268646);
        Pais p5 = new Pais_Comparable_Nome("noruega", "europa", 5.26, "oslo", 59.9138204, 10.7387413);
        Pais p6 = new Pais_Comparable_Nome("suica", "europa", 8.42, "berna", 46.9479986, 7.4481481);

        instance.mapa_paises_nome.put(p1.getNome(), p1);
        instance.mapa_paises_nome.put(p2.getNome(), p2);
        instance.mapa_paises_nome.put(p3.getNome(), p3);
        instance.mapa_paises_nome.put(p4.getNome(), p4);
        instance.mapa_paises_nome.put(p5.getNome(), p5);
        instance.mapa_paises_nome.put(p6.getNome(), p6);
        instance.insertIntoArvore_Nome();

        LinkedList<String> expResult = new LinkedList<>();
        expResult.add(p1.getNome());
        expResult.add(p2.getNome());
        expResult.add(p3.getNome());
        expResult.add(p4.getNome());
        expResult.add(p5.getNome());
        expResult.add(p6.getNome());

        LinkedList<String> result = new LinkedList<>();
        for (Pais p : instance.arvore_nome.inOrder()) {
            result.add(p.getNome());
        }

        assertEquals(expResult, result);
        System.out.println("\n");
    }

    /**
     * Test of insertIntoArvore_KD method, of class Mapa.
     */
    @Test
    public void testInsertIntoArvore_KD() {
        System.out.println("insertIntoArvore_KD");

        Mapa instance = new Mapa();
        Pais p1 = new Pais_Comparable_NFronteiras_Populacao("suica", "europa", 8.42, "berna", 46.9479986, 7.4481481);
        Pais p2 = new Pais_Comparable_NFronteiras_Populacao("eslovaquia", "europa", 5.44, "bratislava", 48.1483765, 17.1073105);
        Pais p3 = new Pais_Comparable_NFronteiras_Populacao("montenegro", "europa", 0.62, "podgorica", 42.442575, 19.268646);
        Pais p4 = new Pais_Comparable_NFronteiras_Populacao("bosnia", "europa", 3.75, "sarajevo", 43.85643, 18.41342);
        Pais p5 = new Pais_Comparable_NFronteiras_Populacao("alemanha", "europa", 82.8, "berlim", 52.5234051, 13.4113999);
        Pais p6 = new Pais_Comparable_NFronteiras_Populacao("noruega", "europa", 5.26, "oslo", 59.9138204, 10.7387413);

        instance.mapa_paises.put(p1.getNome(), p1);
        instance.mapa_paises.put(p2.getNome(), p2);
        instance.mapa_paises.put(p3.getNome(), p3);
        instance.mapa_paises.put(p4.getNome(), p4);
        instance.mapa_paises.put(p5.getNome(), p5);
        instance.mapa_paises.put(p6.getNome(), p6);
        instance.insertIntoArvore_KD();

        LinkedList<Pais_Comparable_NFronteiras_Populacao> expResult = new LinkedList<>();
        expResult.add((Pais_Comparable_NFronteiras_Populacao) p1);
        expResult.add((Pais_Comparable_NFronteiras_Populacao) p2);
        expResult.add((Pais_Comparable_NFronteiras_Populacao) p3);
        expResult.add((Pais_Comparable_NFronteiras_Populacao) p4);
        expResult.add((Pais_Comparable_NFronteiras_Populacao) p5);
        expResult.add((Pais_Comparable_NFronteiras_Populacao) p6);

        LinkedList<Pais_Comparable_NFronteiras_Populacao> result = new LinkedList<>();
        for (Pais p : instance.arvore_2d.inOrder()) {
            result.add((Pais_Comparable_NFronteiras_Populacao) p);
        }

        //Verificar se o node à esquerda do root tem menor LATITUDE e se o à direita tem maior LATITUDE
        boolean expResultBoolean1 = true;
        boolean resultBoolean1 = false;
        Double rootLatitude = instance.arvore_2d.root().getElement().getLatitude();
        Double rootLeftChildren = instance.arvore_2d.root().getLeft().getElement().getLatitude();
        Double rootRightChildren = instance.arvore_2d.root().getRight().getElement().getLatitude();
        if (rootLeftChildren < rootLatitude && rootRightChildren > rootLatitude && instance.arvore_2d.getLevel(instance.arvore_2d.root().getElement()) % 2 != 0) {
            resultBoolean1 = true;
        }
        
        //Verificar se o node filho da root à esquerda, tem à sua esquerda um children com menor LONGITUDE e à sua direita um com maior LONGITUDE
        boolean expResultBoolean2 = true;
        boolean resultBoolean2 = false;
        Double rootLeftNode = instance.arvore_2d.root().getLeft().getElement().getLongitude();
        Double nodeLeftChildren = instance.arvore_2d.root().getLeft().getLeft().getElement().getLongitude();
        Double nodeRightChildren = instance.arvore_2d.root().getLeft().getRight().getElement().getLongitude();
        if (nodeLeftChildren < rootLeftNode && nodeRightChildren > rootLeftNode && instance.arvore_2d.getLevel(instance.arvore_2d.root().getLeft().getElement()) % 2 == 0) {
            resultBoolean2 = true;
        }
        
        //Verificar se o node filho do node anterior é comparado devidamente por LATITUDE
        boolean expResultBoolean3 = true;
        boolean resultBoolean3 = false;
        Double rootLeftRight = instance.arvore_2d.root().getLeft().getRight().getElement().getLatitude();
        if (instance.arvore_2d.root().getLeft().getRight().getLeft() == null && instance.arvore_2d.root().getLeft().getRight().getRight() != null) {
            Double nodeRightChildren2 = instance.arvore_2d.root().getLeft().getRight().getRight().getElement().getLatitude();
            if (nodeRightChildren2 > rootLeftRight && instance.arvore_2d.getLevel(instance.arvore_2d.root().getLeft().getRight().getElement()) % 2 != 0) {
                resultBoolean3 = true;
            }
        }

        assertEquals(expResultBoolean1, resultBoolean1);
        assertEquals(expResultBoolean2, resultBoolean2);
        assertEquals(expResultBoolean3, resultBoolean3);
        assertEquals(expResult, result);
        System.out.println("\n");
    }

    /**
     * Test of getPaisByCoordenadas method, of class Mapa.
     */
    @Test
    public void testGetPaisByCoordenadas() {
        System.out.println("-----* EXERCÍCIO 2 b) *-----");
        System.out.println("1º Teste: getPaisByCoordenadas");
        System.out.println("Pesquisa Exata: devolve o país cuja capital esteja situada nas coordenadas fornecidas\n");
        double latitude = 52.2296756;
        double longitude = 21.0122287;

        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");

        Pais expResult = new Pais_Comparable_NFronteiras_Populacao("polonia", "europa", 38.42, "varsovia", 52.2296756, 21.0122287);
        Pais result = instance.getPaisByCoordenadas(latitude, longitude);

        assertEquals(expResult, result);

        System.out.println("Coordenadas da Polónia\n---------------------");
        System.out.println("Latitude : " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.printf("\nOutput:\n| País: %s | Capital: %s | População: %3s | Latitude: %3s | Longitude: %3s |%n", expResult.getNome(), expResult.getCapital(), expResult.getPopulacao(), expResult.getLatitude(), expResult.getLongitude());
        System.out.println("\n------------------------------------------------------------\n");
    }

    /**
     * Test of getPaisByCoordenadas method, of class Mapa.
     */
    @Test
    public void testGetPaisByCoordenadas2() {
        System.out.println("-----* EXERCÍCIO 2 b) *-----");
        System.out.println("2º Teste: getPaisByCoordenadas");
        System.out.println("Pesquisa Exata: devolve o país cuja capital esteja situada nas coordenadas fornecidas\n");
        double latitude = 52.3738007;
        double longitude = 4.8909347;

        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais expResult = new Pais_Comparable_NFronteiras_Populacao("holanda", "europa", 17.08, "amsterdam", 52.3738007, 4.8909347);
        Pais result = instance.getPaisByCoordenadas(latitude, longitude);

        assertEquals(expResult, result);

        System.out.println("Coordenadas da Holanda\n---------------------");
        System.out.println("Latitude : " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.printf("\nOutput:\n| País: %s | Capital: %s | População: %3s | Latitude: %3s | Longitude: %3s |%n", expResult.getNome(), expResult.getCapital(), expResult.getPopulacao(), expResult.getLatitude(), expResult.getLongitude());
        System.out.println("\n------------------------------------------------------------\n");
    }
    
    /**
     * Test of getVizinhoMaisProximo method, of class Mapa.
     */
    @Test
    public void testVizinhoMaisProximo() {
        System.out.println("Teste: vizinhoMaisProximo");
        //Failsafe tests
        Mapa instance = new Mapa();
        Pais p1 = new Pais_Comparable_NFronteiras_Populacao("noruega", "europa", 5.26, "oslo", 59.9138204, 10.7387413);
        instance.mapa_paises.put(p1.getNome(), p1);
        instance.insertIntoArvore_KD();
        
        Pais expResult = p1;
        Pais result = instance.vizinhoMaisProximo(instance.arvore_2d.root.getElement().getLatitude(), instance.arvore_2d.root.getElement().getLongitude());
        assertEquals(expResult, result);
    }

    /**
     * Test of vizinhoMaisProximo method, of class Mapa.
     */
    @Test
    public void testGetVizinhoMaisProximo() {
        System.out.println("-----* EXERCÍCIO 2 c) *-----");
        System.out.println("1º Teste: vizinhoMaisProximo\n\n\tÁRVORE 2d:\n");
        
        Mapa instance = new Mapa();
        Pais p1 = new Pais_Comparable_NFronteiras_Populacao("suica", "europa", 8.42, "berna", 46.9479986, 7.4481481);
        Pais p2 = new Pais_Comparable_NFronteiras_Populacao("eslovaquia", "europa", 5.44, "bratislava", 48.1483765, 17.1073105);
        Pais p3 = new Pais_Comparable_NFronteiras_Populacao("montenegro", "europa", 0.62, "podgorica", 42.442575, 19.268646);
        Pais p4 = new Pais_Comparable_NFronteiras_Populacao("bosnia", "europa", 3.75, "sarajevo", 43.85643, 18.41342);
        Pais p5 = new Pais_Comparable_NFronteiras_Populacao("alemanha", "europa", 82.8, "berlim", 52.5234051, 13.4113999);
        Pais p6 = new Pais_Comparable_NFronteiras_Populacao("noruega", "europa", 5.26, "oslo", 59.9138204, 10.7387413);

        instance.mapa_paises.put(p1.getNome(), p1);
        instance.mapa_paises.put(p2.getNome(), p2);
        instance.mapa_paises.put(p3.getNome(), p3);
        instance.mapa_paises.put(p4.getNome(), p4);
        instance.mapa_paises.put(p5.getNome(), p5);
        instance.mapa_paises.put(p6.getNome(), p6);
        instance.insertIntoArvore_KD();
        
        System.out.println(instance.arvore_2d + "\n");
        Pais expResult = p1;
        Pais result = instance.vizinhoMaisProximo(0, 0);
        assertEquals(expResult, result);
        
        System.out.println("País esperado é 'Suiça' e resultado é: " + result.getNome().toUpperCase());
        System.out.println("\n------------------------------------------------------------\n");
    }
    
    /**
     * Test of vizinhoMaisProximo method, of class Mapa.
     */
    @Test
    public void testGetVizinhoMaisProximo2() {
        System.out.println("-----* EXERCÍCIO 2 c) *-----");
        System.out.println("2º Teste: vizinhoMaisProximo\n\n\tÁRVORE 2d:\n");
        
        Mapa instance = new Mapa();
        Pais p1 = new Pais_Comparable_NFronteiras_Populacao("suica", "europa", 8.42, "berna", 46.9479986, 7.4481481);
        Pais p2 = new Pais_Comparable_NFronteiras_Populacao("eslovaquia", "europa", 5.44, "bratislava", 48.1483765, 17.1073105);
        Pais p3 = new Pais_Comparable_NFronteiras_Populacao("montenegro", "europa", 0.62, "podgorica", 42.442575, 19.268646);
        Pais p4 = new Pais_Comparable_NFronteiras_Populacao("bosnia", "europa", 3.75, "sarajevo", 43.85643, 18.41342);
        Pais p5 = new Pais_Comparable_NFronteiras_Populacao("alemanha", "europa", 82.8, "berlim", 52.5234051, 13.4113999);
        Pais p6 = new Pais_Comparable_NFronteiras_Populacao("noruega", "europa", 5.26, "oslo", 59.9138204, 10.7387413);

        instance.mapa_paises.put(p1.getNome(), p1);
        instance.mapa_paises.put(p2.getNome(), p2);
        instance.mapa_paises.put(p3.getNome(), p3);
        instance.mapa_paises.put(p4.getNome(), p4);
        instance.mapa_paises.put(p5.getNome(), p5);
        instance.mapa_paises.put(p6.getNome(), p6);
        instance.insertIntoArvore_KD();
        
        System.out.println(instance.arvore_2d + "\n");
        Pais expResult = p3;
        Pais result = instance.vizinhoMaisProximo(40, 20);
        assertEquals(expResult, result);
        
        System.out.println("País esperado é 'Montenegro' e resultado é: " + result.getNome().toUpperCase());
        System.out.println("\n------------------------------------------------------------\n");
    }
    
    /**
     * Test of vizinhoMaisProximo method, of class Mapa.
     */
    @Test
    public void testGetVizinhoMaisProximo3() {
        System.out.println("-----* EXERCÍCIO 2 c) *-----\n");
        
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        
        Pais expResult00 = new Pais_Comparable_NFronteiras_Populacao("malta", "europa", 0.44, "valletta", 35.904171, 14.518907);
        Pais expResult74_14 = new Pais_Comparable_NFronteiras_Populacao("islandia", "europa", 0.34, "reiquiavique", 64.135338, -21.89521);
        Pais expResult12_5 = new Pais_Comparable_NFronteiras_Populacao("espanha", "europa", 46.53, "madrid", 40.4166909, -3.7003454);
        Pais expResult3030 = new Pais_Comparable_NFronteiras_Populacao("chipre", "europa", 0.85, "nicosia", 35.167604, 33.373621);
        Pais expResult4691 = new Pais_Comparable_NFronteiras_Populacao("russia", "europa", 146.5, "moscovo", 55.755786, 37.617633);
        Pais result = instance.vizinhoMaisProximo(0, 0);
        Pais result2 = instance.vizinhoMaisProximo(76, -14);
        Pais result3 = instance.vizinhoMaisProximo(12, -5);
        Pais result4 = instance.vizinhoMaisProximo(30, 30);
        Pais result5 = instance.vizinhoMaisProximo(46, 91);

        assertEquals(expResult00, result);
        assertEquals(expResult74_14, result2);
        assertEquals(expResult12_5, result3);
        assertEquals(expResult3030, result4);
        assertEquals(expResult4691, result5);
        
        System.out.println("País mais próximo de 0'   0' é Malta e resultado é:    " + result.getNome().toUpperCase());
        System.out.println("País mais próximo de 74'-14' é Islândia e resultado é: " + result2.getNome().toUpperCase());
        System.out.println("País mais próximo de 12' -5' é Espanha e resultado é:  " + result3.getNome().toUpperCase());
        System.out.println("País mais próximo de 30' 30' é Chipre e resultado é:   " + result4.getNome().toUpperCase());
        System.out.println("País mais próximo de 46' 91' é Rússia e resultado é:   " + result5.getNome().toUpperCase());
        System.out.println("\n------------------------------------------------------------\n");
    }
    
    /**
     * Test of calculoDistancia method, of class Mapa.
     */
    @Test
    public void testCalculoDistancia() {
        System.out.println("Teste: calculoDistancia");
        Mapa instance = new Mapa();
        double latitude = 50;
        double longitude = 25;
        
        Pais paisTeste = new Pais_Comparable_NFronteiras_Populacao("bosnia", "europa", 3.75, "sarajevo", 43.85643, 18.41342);
        Pais paisTeste2 = new Pais_Comparable_NFronteiras_Populacao("montenegro", "europa", 0.62, "podgorica", 42.442575, 19.268646);
        instance.mapa_paises.put(paisTeste.getNome(), paisTeste);
        instance.mapa_paises.put(paisTeste2.getNome(), paisTeste2);
        instance.insertIntoArvore_KD();
        
        Node<Pais> pais = new Node<>(paisTeste, instance.arvore_2d.root, instance.arvore_2d.root);
        Node<Pais> pais2 = new Node<>(paisTeste2, instance.arvore_2d.root.getLeft(), instance.arvore_2d.root.getLeft());
        
        double expResult = 81.12648844129998; //resultado esperado = (50 - 43.85643)^2 + (25 - 18.41342)^2 = 81.12648844129998
        double result = instance.calculoDistancia(latitude, longitude, pais);
        double expResult2 = 89.96309130394103;
        double result2 = instance.calculoDistancia(latitude, longitude, pais2);
        
        assertEquals(expResult, result, 0.0);
        assertEquals(expResult2, result2, 0.0);
    }

    /**
     * Test of pesquisaAreaGeografica method, of class Mapa.
     */
    @Test
    public void testPesquisaAreaGeografica_4args() {
        System.out.println("-----* EXERCÍCIO 2 d) - Teste 1 *-----\n");
        double latitude1 = 48.8566667;
        double longitude1 = 2.3509871;
        double latitude2 = 41.8954656;
        double longitude2 = 12.4823243;
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        HashSet<Pais> expResult = new HashSet<>();
        Pais p1 = new Pais_Comparable_NFronteiras_Populacao("italia", "europa", 60.59, "roma", 41.8954656, 12.4823243);
        Pais p2 = new Pais_Comparable_NFronteiras_Populacao("suica", "europa", 8.42, "berna", 46.9479986, 7.4481481);
        Pais p3 = new Pais_Comparable_NFronteiras_Populacao("liechtenstein", "europa", 0.04, "vaduz", 47.1410409, 9.5214458);
        Pais p4 = new Pais_Comparable_NFronteiras_Populacao("franca", "europa", 66.99, "paris", 48.8566667, 2.3509871);
        Pais p5 = new Pais_Comparable_NFronteiras_Populacao("monaco", "europa", 0.04, "monaco", 43.750298, 7.412841);
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        expResult.add(p5);
        expResult.add(p4);
        HashSet<Pais> result = instance.pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2);
        
        assertTrue(expResult.equals(result));
        assertEquals(5, result.size());
        for(Pais p : result){
            if (!expResult.contains(p)) {
                fail();
            }
        }
        
        System.out.println("A lista de paises esperada entre os pontos (48.8566667, 2.3509871) e (41.8954656, 12.4823243) é "+expResult);
        System.out.println("O resultado obtido é "+result);
        System.out.println("\n------------------------------------------------------------\n");
    }
    
    /**
     * 2nd Test of pesquisaAreaGeografica method, of class Mapa.
     */
    @Test
    public void test2PesquisaAreaGeografica_4args() {
        System.out.println("-----* EXERCÍCIO 2 d) - Teste 2 *-----\n");
        double latitude1 = 48.8566667;
        double longitude1 = 2.3509871;
        double latitude2 = 53.344104;
        double longitude2 = -6.2674937;
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        HashSet<Pais> expResult = new HashSet<>();
        HashSet<Pais> result = instance.pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2);
        
        assertTrue(expResult.equals(result));
        assertEquals(0, result.size());
        
        System.out.println("A lista de paises esperada entre os pontos (48.8566667, 2.3509871) e (53.344104, -6.2674937) é "+expResult);
        System.out.println("O resultado obtido é "+result);
        System.out.println("A lista é vazia pois o ponto superior esquerdo da área rectangular é inferior na coordenada da latitude em relação ao ponto inferior direito.");
        System.out.println("\n------------------------------------------------------------\n");
    }
    
    /**
     * 3rd Test of pesquisaAreaGeografica method, of class Mapa.
     */
    @Test
    public void test3PesquisaAreaGeografica_4args() {
        System.out.println("-----* EXERCÍCIO 2 d) - Teste 3 *-----\n");
        double latitude1 = 48.8566667;
        double longitude1 = 2.3509871;
        double latitude2 = 53.344104;
        double longitude2 = -6.2674937;
        Mapa instance = new Mapa();
        HashSet<Pais> expResult = new HashSet<>();
        HashSet<Pais> result = instance.pesquisaAreaGeografica(latitude1, longitude1, latitude2, longitude2);
        
        assertEquals(expResult, result);
        
        System.out.println("A lista de paises esperada entre os pontos (48.8566667, 2.3509871) e (53.344104, -6.2674937) é "+expResult);
        System.out.println("O resultado obtido é "+result);
        System.out.println("O método retorna uma lista vazia quando a árvore está vazia ou a root tem valor null");
        System.out.println("\n------------------------------------------------------------\n");
    }
    
}
