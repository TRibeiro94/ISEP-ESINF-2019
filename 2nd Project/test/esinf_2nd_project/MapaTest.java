package esinf_2nd_project;

import esinf_2nd_project.Pais;
import esinf_2nd_project.Mapa;
import graphbase.Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.util.Pair;
import matrixgraph.AdjacencyMatrix;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.Utils;

/**
 * @author Bernardo
 * @author Tiago
 */
public class MapaTest {
    
    public MapaTest() {
    }
    
    /**
     * Test of addPais method, of class Mapa.
     */
    @Test
    public void testAddPais() {
        System.out.println("CLASSE MAPA - TESTES\n--------------------\n");
        System.out.println("Teste: addPais");
        Mapa instance = new Mapa();
        
        Pais portugal = new Pais("Portugal", "Europa", 10.31, "Lisboa", 38.7071631, -9.135517);
        Pais noruega = new Pais("Noruega", "Europa", 5.26, "Oslo", 59.9138204, 10.7387413);
        instance.addPais(portugal);
        instance.addPais(noruega);
        int keyEsperada = 1;
        int key = instance.grafo.getKey(noruega);
        assertEquals(keyEsperada,key);
        System.out.println("Conteúdo do grafo após inserção de Portugal e Turquia:");
        for(Pais p: instance.grafo.vertices()){
            System.out.println("País: "+p.getNome()+", "+p.getContinente()+", "+p.getCapital());
        }
        System.out.println("------------------------------------------------------\n");
    }

    /**
     * Test of addFronteira method, of class Mapa.
     */
    @Test
    public void testAddFronteira() {
        System.out.println("Teste: addFronteira");
        System.out.println("É adicionado Portugal e Espanha e verificados os elementos com o getEdge:");
        Mapa instance = new Mapa();
        
        Pais p1 = new Pais("Portugal", "Europa", 10.31, "Lisboa", 38.7071631, -9.135517);
        Pais p2 = new Pais("Espanha", "europa", 46.53, "Madrid", 40.4166909, -3.7003454);
        double distancia = Utils.calcularDistancia(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
        instance.addFronteira(p1, p2, distancia);
        
        Pais origem = instance.grafo.getEdge(p1, p2).getVOrig();
        Pais destino = instance.grafo.getEdge(p1, p2).getVDest();
        
        System.out.println(instance.grafo.getEdge(p1, p2).getVOrig() + " <--> " + instance.grafo.getEdge(p1, p2).getVDest());
        assertEquals(origem, p1);
        assertEquals(destino, p2);
        System.out.println("\n------------------------------------------------------\n");
    }

    /**
     * Test of getPaisByNome method, of class Mapa.
     */
    @Test
    public void testGetPaisByNome() {
        System.out.println("Teste: getPaisByNome");
        System.out.println("Devolve o objecto do tipo Pais através da string do seu nome:");
        String nome = "Dinamarca";
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        
        Pais expResult = new Pais("dinamarca", "europa", 5.75, "copenhaga", 55.6762944, 12.5681157);
        Pais result = instance.getPaisByNome(nome);
        
        System.out.println("String : " + nome);
        System.out.println("País: "+result.getNome()+", "+result.getContinente()+", "+result.getCapital()+", "+result.getPopulacao()+"M de habitantes.");
        assertEquals(expResult, result);
        System.out.println("\n------------------------------------------------------\n");
    }

    /**
     * Test of getPaisByCapital method, of class Mapa.
     */
    @Test
    public void testGetPaisByCapital() {
        System.out.println("Teste: getPaisByCapital");
        System.out.println("Devolve o objecto do tipo Pais através da string da sua capital:");
        String nome = "varsovia";
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        
        Pais expResult = new Pais("polonia", "europa", 38.42, "varsovia", 52.2296756, 21.0122287);
        Pais result = instance.getPaisByCapital(nome);
        
        System.out.println("String: "+ nome);
        System.out.println("País: "+result.getNome()+", "+result.getContinente()+", "+result.getCapital()+", "+result.getPopulacao()+"M de habitantes.");
        assertEquals(expResult, result);
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of getPaisByKey method, of class Mapa.
     */
    @Test
    public void testGetPaisByKey(){
        System.out.println("Teste: getPaisByKey");
        System.out.println("Devolve o objecto do tipo Pais através da sua key identificadora no grafo:");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        
        Pais chipre = new Pais("chipre", "europa", 0.85, "nicosia", 35.167604, 33.373621);
        Pais lituania = new Pais("lituania", "europa", 2.85, "vilnius", 54.6893865, 25.2800243);
        Pais uruguai = new Pais("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600);
        
        int keyChipre = instance.grafo.getKey(chipre);
        int keyLituania = instance.grafo.getKey(lituania);
        int keyUruguai = instance.grafo.getKey(uruguai);
        
        Pais p1 = instance.getPaisByKey(keyChipre);
        Pais p2 = instance.getPaisByKey(keyLituania);
        Pais p3 = instance.getPaisByKey(keyUruguai);
        System.out.println("Key do Chipre no mapa:  "   + keyChipre);
        System.out.println("País devolvido através da key ["+keyChipre+"]: "+instance.getPaisByKey(keyChipre).getNome().toUpperCase()+", Capital: "+instance.getPaisByKey(keyChipre).getCapital());
        System.out.println("Key da Lituania no mapa:  " + keyLituania);
        System.out.println("País devolvido através da key ["+keyLituania+"]: "+instance.getPaisByKey(keyLituania).getNome().toUpperCase()+", Capital: "+instance.getPaisByKey(keyLituania).getCapital());
        System.out.println("Key do Uruguai no mapa:  "  + keyUruguai);
        System.out.println("País devolvido através da key ["+keyUruguai+"]: "+instance.getPaisByKey(keyUruguai).getNome().toUpperCase()+", Capital: "+instance.getPaisByKey(keyUruguai).getCapital());
        assertEquals(chipre, p1);
        assertEquals(lituania, p2);
        assertEquals(uruguai, p3);
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of caminhoMaisCurto method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisCurto(){
        System.out.println("-----* EXERCÍCIO 3 *-----");
        System.out.println("1º Teste: CaminhoMaisCurto\n");
        
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais origem = new Pais("grecia", "europa", 10.76, "atenas", 37.97918, 23.716647);
        Pais destino = new Pais("polonia", "europa", 38.42, "varsovia", 52.2296756, 21.0122287);
        
        System.out.println("Distância entre Grécia e Polónia: " + Utils.metersToKm(instance.caminhoMaisCurto(origem, destino).getKey()));
        System.out.printf("Percurso: [ ");
        for(Pais p: instance.caminhoMaisCurto(origem, destino).getValue()){
            System.out.printf(p.getNome().toUpperCase()+" ");
        }
        System.out.printf("]\n\n");
        
        double dist1 = Utils.calcularDistancia(origem.getLatitude(), origem.getLongitude(), instance.getPaisByNome("macedonia").getLatitude(), instance.getPaisByNome("macedonia").getLongitude());
        double dist2 = Utils.calcularDistancia(instance.getPaisByNome("macedonia").getLatitude(), instance.getPaisByNome("macedonia").getLongitude(), instance.getPaisByNome("servia").getLatitude(), instance.getPaisByNome("servia").getLongitude());
        double dist3 = Utils.calcularDistancia(instance.getPaisByNome("servia").getLatitude(), instance.getPaisByNome("servia").getLongitude(), instance.getPaisByNome("hungria").getLatitude(), instance.getPaisByNome("hungria").getLongitude());
        double dist4 = Utils.calcularDistancia(instance.getPaisByNome("hungria").getLatitude(), instance.getPaisByNome("hungria").getLongitude(), instance.getPaisByNome("eslovaquia").getLatitude(), instance.getPaisByNome("eslovaquia").getLongitude());
        double dist5 = Utils.calcularDistancia(instance.getPaisByNome("eslovaquia").getLatitude(), instance.getPaisByNome("eslovaquia").getLongitude(), instance.getPaisByNome("polonia").getLatitude(), instance.getPaisByNome("polonia").getLongitude());
        double soma = dist1 + dist2 + dist3 + dist4 + dist5;
        
        System.out.println("Grécia a Macedónia: "   + Utils.metersToKm(dist1));
        System.out.println("Macedónia a Sérvia: "   + Utils.metersToKm(dist2));
        System.out.println("Sérvia a Hungria: "     + Utils.metersToKm(dist3));
        System.out.println("Hungria a Eslováquia: " + Utils.metersToKm(dist4));
        System.out.println("Eslováquia a Polónia: " + Utils.metersToKm(dist5) + "\n");
        System.out.println("Distância total (m): "  + soma + " m");
        System.out.println("Distância total (km): " + Utils.metersToKm(soma));
        double expResult = soma;
        double result = instance.caminhoMaisCurto(origem, destino).getKey();
        assertEquals(expResult, result, 0.0);
        
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of caminhoMaisCurto method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisCurto2(){
        System.out.println("-----* EXERCÍCIO 3 *-----");
        System.out.println("2º Teste: CaminhoMaisCurto\n");
        
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais origem = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Pais destino = new Pais("russia", "europa", 146.5, "moscovo", 55.755786, 37.617633);
        
        System.out.println("Distância entre Portugal e Rússia: " + Utils.metersToKm(instance.caminhoMaisCurto(origem, destino).getKey()));
        System.out.printf("Percurso: [ ");
        for(Pais p: instance.caminhoMaisCurto(origem, destino).getValue()){
            System.out.printf(p.getNome().toUpperCase()+" ");
        }
        System.out.printf("]\n\n");
        
        double dist1 = Utils.calcularDistancia(origem.getLatitude(), origem.getLongitude(), instance.getPaisByNome("espanha").getLatitude(), instance.getPaisByNome("espanha").getLongitude());
        double dist2 = Utils.calcularDistancia(instance.getPaisByNome("espanha").getLatitude(), instance.getPaisByNome("espanha").getLongitude(), instance.getPaisByNome("franca").getLatitude(), instance.getPaisByNome("franca").getLongitude());
        double dist3 = Utils.calcularDistancia(instance.getPaisByNome("franca").getLatitude(), instance.getPaisByNome("franca").getLongitude(), instance.getPaisByNome("alemanha").getLatitude(), instance.getPaisByNome("alemanha").getLongitude());
        double dist4 = Utils.calcularDistancia(instance.getPaisByNome("alemanha").getLatitude(), instance.getPaisByNome("alemanha").getLongitude(), instance.getPaisByNome("polonia").getLatitude(), instance.getPaisByNome("polonia").getLongitude());
        double dist5 = Utils.calcularDistancia(instance.getPaisByNome("polonia").getLatitude(), instance.getPaisByNome("polonia").getLongitude(), instance.getPaisByNome("russia").getLatitude(), instance.getPaisByNome("russia").getLongitude());
        double soma = dist1 + dist2 + dist3 + dist4 + dist5;
        
        System.out.println("Portugal a Espanha: "   + Utils.metersToKm(dist1));
        System.out.println("Espanha a França: "     + Utils.metersToKm(dist2));
        System.out.println("França a Alemanha: "    + Utils.metersToKm(dist3));
        System.out.println("Alemanha a Polónia: "   + Utils.metersToKm(dist4));
        System.out.println("Polónia a Rússia: "     + Utils.metersToKm(dist5) + "\n");
        System.out.println("Distância total (m): "  + soma + " m");
        System.out.println("Distância total (km): " + Utils.metersToKm(soma));
        double expResult = soma;
        double result = instance.caminhoMaisCurto(origem, destino).getKey();
        assertEquals(expResult, result, 0.0);
        
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of caminhoMaisCurto method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisCurto3(){
        System.out.println("-----* EXERCÍCIO 3 *-----");
        System.out.println("3º Teste: CaminhoMaisCurto\n");
        
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais origem = new Pais("belgica", "europa", 11.37, "bruxelas", 50.8462807, 4.3547273);
        Pais destino = new Pais("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        
        System.out.println("Distância entre Bélgica e Brasil: " + instance.caminhoMaisCurto(origem, destino).getKey());
        System.out.printf("Percurso: [ ");
        for(Pais p: instance.caminhoMaisCurto(origem, destino).getValue()){
            System.out.printf(p.getNome().toUpperCase()+" ");
        }
        System.out.printf("]\n\n");
        System.out.println("Percurso e distância são nulos pois não é possível atravessar Continentes.");
        double expResult = 0;
        double result = instance.caminhoMaisCurto(origem, destino).getKey();
        assertEquals(expResult, result, 0.0);
        
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of caminhoMaisCurto method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisCurto4(){
        System.out.println("-----* EXERCÍCIO 3 *-----");
        System.out.println("4º Teste: CaminhoMaisCurto\n");
        
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais origem = new Pais("franca", "europa", 66.99, "paris", 48.8566667, 2.3509871);
        Pais destino = new Pais("islandia", "europa", 0.34, "reiquiavique", 64.135338, -21.89521);
        
        System.out.println("Distância entre França e Islândia: " + instance.caminhoMaisCurto(origem, destino).getKey());
        System.out.printf("Percurso: [ ");
        for(Pais p: instance.caminhoMaisCurto(origem, destino).getValue()){
            System.out.printf(p.getNome().toUpperCase()+" ");
        }
        System.out.printf("]\n\n");
        System.out.println("Percurso e distância são nulos pois não é possível chegar ao destino.");
        double expResult = 0;
        double result = instance.caminhoMaisCurto(origem, destino).getKey();
        assertEquals(expResult, result, 0.0);
        
        System.out.println("\n------------------------------------------------------\n");
    }

    /**
     * Test of colorirMapa method, of class Mapa.
     */
    @Test
    public void testColorirMapa() {
        System.out.println("     -----* EXERCÍCIO 2 *-----");
        System.out.println("         Teste: ColorirMapa");
        
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        instance.colorirMapa();
    }

    /**
     * Test of caminhoMaisCurtoComPaisesObrigatorios method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisCurtoComPaisesObrigatorios() {
        System.out.println("-----* EXERCÍCIO 4 *-----");
        System.out.println("1º Teste: caminhoMaisCurtoComPaisesObrigatorios");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais orig = instance.getPaisByNome("portugal");
        Pais dest = instance.getPaisByNome("russia");
        Pais p1 = instance.getPaisByNome("holanda");
        Pais p2 = instance.getPaisByNome("grecia");
        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(p1);
        paises.add(p2);
        ArrayList<Pais> caminhoCompleto = new ArrayList<>();
        caminhoCompleto.add(orig);
        caminhoCompleto.add(instance.getPaisByNome("espanha"));
        caminhoCompleto.add(instance.getPaisByNome("franca"));
        caminhoCompleto.add(instance.getPaisByNome("belgica"));
        caminhoCompleto.add(instance.getPaisByNome("holanda"));
        caminhoCompleto.add(instance.getPaisByNome("alemanha"));
        caminhoCompleto.add(instance.getPaisByNome("republicacheca"));
        caminhoCompleto.add(instance.getPaisByNome("eslovaquia"));
        caminhoCompleto.add(instance.getPaisByNome("hungria"));
        caminhoCompleto.add(instance.getPaisByNome("servia"));
        caminhoCompleto.add(instance.getPaisByNome("macedonia"));
        caminhoCompleto.add(instance.getPaisByNome("grecia"));
        caminhoCompleto.add(instance.getPaisByNome("bulgaria"));
        caminhoCompleto.add(instance.getPaisByNome("romenia"));
        caminhoCompleto.add(instance.getPaisByNome("ucrania"));
        caminhoCompleto.add(dest);
        
        Pair<Double, ArrayList<Pais>> expResult = new Pair<Double, ArrayList<Pais>>(6755542.355111625, caminhoCompleto);
        Pair<Double, ArrayList<Pais>> result = instance.caminhoMaisCurtoComPaisesObrigatorios(orig, dest, paises);
        assertEquals(expResult, result);
        System.out.println("");
        System.out.println("Distância entre portugal e rússia (passando obrigatóriamente por holanda e grécia): "+Utils.metersToKm(result.getKey()));
        System.out.println("Caminho Completo: "+result.getValue());
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of caminhoMaisCurtoComPaisesObrigatorios method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisCurtoComPaisesObrigatorios2() {
        System.out.println("-----* EXERCÍCIO 4 *-----");
        System.out.println("2º Teste: caminhoMaisCurtoComPaisesObrigatorios");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais orig = instance.getPaisByNome("portugal");
        Pais dest = instance.getPaisByNome("reinounido");
        Pais p1 = instance.getPaisByNome("franca");
        Pais p2 = instance.getPaisByNome("suica");
        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(p1);
        paises.add(p2);
        
        Pair<Double, ArrayList<Pais>> expResult = null;
        Pair<Double, ArrayList<Pais>> result = instance.caminhoMaisCurtoComPaisesObrigatorios(orig, dest, paises);
        assertEquals(expResult, result);
        System.out.println("");
        System.out.println("Distância entre portugal e reino unido (passando obrigatóriamente por frança e suiça): 0.0 km");
        System.out.println("Caminho Completo: [ ]");
        System.out.println("Distância e caminho completo são nulos pois não é possível chegar ao destino.");
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of caminhoMaisCurtoComPaisesObrigatorios method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisCurtoComPaisesObrigatorios3() {
        System.out.println("-----* EXERCÍCIO 4 *-----");
        System.out.println("3º Teste: caminhoMaisCurtoComPaisesObrigatorios");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais orig = instance.getPaisByNome("portugal");
        Pais dest = instance.getPaisByNome("alemanha");
        Pais p1 = instance.getPaisByNome("franca");
        Pais p2 = instance.getPaisByNome("irao");
        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(p1);
        paises.add(p2);
        
        Pair<Double, ArrayList<Pais>> expResult = null;
        Pair<Double, ArrayList<Pais>> result = instance.caminhoMaisCurtoComPaisesObrigatorios(orig, dest, paises);
        assertEquals(expResult, result);
        System.out.println("");
        System.out.println("Distância entre portugal e alemanha (passando obrigatóriamente por frança e irão): 0.0 km");
        System.out.println("Caminho Completo: [ ]");
        System.out.println("Distância e caminho completo são nulos pois um dos paises obrigatórios de passagem não existe no grafo.");
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of caminhoMaisCurtoComPaisesObrigatorios method, of class Mapa.
     */
    @Test
    public void testCaminhoMaisCurtoComPaisesObrigatorios4() {
        System.out.println("-----* EXERCÍCIO 4 *-----");
        System.out.println("4º Teste: caminhoMaisCurtoComPaisesObrigatorios");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais orig = instance.getPaisByNome("portugal");
        Pais dest = instance.getPaisByNome("alemanha");
        Pais p1 = instance.getPaisByNome("peru");
        Pais p2 = instance.getPaisByNome("espanha");
        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(p1);
        paises.add(p2);
        
        Pair<Double, ArrayList<Pais>> expResult = null;
        Pair<Double, ArrayList<Pais>> result = instance.caminhoMaisCurtoComPaisesObrigatorios(orig, dest, paises);
        assertEquals(expResult, result);
        System.out.println("");
        System.out.println("Distância entre portugal e alemanha (passando obrigatóriamente por peru e espanha): 0.0 km");
        System.out.println("Caminho Completo: [ ]");
        System.out.println("Distância e caminho completo são nulos pois não existe nenhum caminho possivel entre o pais de origem e um dos paises pelos quais é obrigatório passar.");
        System.out.println("\n------------------------------------------------------\n");
    }

    /**
     * Test of maiorCircuito method, of class Mapa.
     */
    @Test
    public void testMaiorCircuito() {
        System.out.println("-----* EXERCÍCIO 5 *-----");
        System.out.println("1º Teste: maiorCircuito");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais pais = instance.getPaisByNome("alemanha");
        LinkedList<Pais> paises = new LinkedList<>();
        LinkedList<Pais> expResult = new LinkedList<>();
        expResult.add(instance.getPaisByNome("alemanha"));
        expResult.add(instance.getPaisByNome("republicacheca"));
        expResult.add(instance.getPaisByNome("austria"));
        expResult.add(instance.getPaisByNome("eslovaquia"));
        expResult.add(instance.getPaisByNome("hungria"));
        expResult.add(instance.getPaisByNome("croacia"));
        expResult.add(instance.getPaisByNome("eslovenia"));
        expResult.add(instance.getPaisByNome("italia"));
        expResult.add(instance.getPaisByNome("suica"));
        expResult.add(instance.getPaisByNome("alemanha"));
        
        LinkedList<Pais> result = instance.maiorCircuito(pais, paises);
        assertEquals(expResult, result);
        
        System.out.println("Circuito Completo a partir da alemanha: \n"+result);
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of maiorCircuito method, of class Mapa.
     */
    @Test
    public void testMaiorCircuito2() {
        System.out.println("-----* EXERCÍCIO 5 *-----");
        System.out.println("2º Teste: maiorCircuito");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais pais = instance.getPaisByNome("portugal");
        LinkedList<Pais> paises = new LinkedList<>();
        LinkedList<Pais> expResult = new LinkedList<>();
        expResult.add(instance.getPaisByNome("portugal"));
        
        LinkedList<Pais> result = instance.maiorCircuito(pais, paises);
        assertEquals(expResult, result);
        
        System.out.println("Circuito Completo a partir de portugal: \n"+result);
        System.out.println("\n------------------------------------------------------\n");
    }
    
    /**
     * Test of maiorCircuito method, of class Mapa.
     */
    @Test
    public void testMaiorCircuito3() {
        System.out.println("-----* EXERCÍCIO 5 *-----");
        System.out.println("3º Teste: maiorCircuito");
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader("fronteiras.txt");
        Pais pais = instance.getPaisByNome("brasil");
        LinkedList<Pais> paises = new LinkedList<>();
        LinkedList<Pais> expResult = new LinkedList<>();
        expResult.add(instance.getPaisByNome("brasil"));
        expResult.add(instance.getPaisByNome("paraguai"));
        expResult.add(instance.getPaisByNome("argentina"));
        expResult.add(instance.getPaisByNome("uruguai"));
        expResult.add(instance.getPaisByNome("brasil"));
        
        LinkedList<Pais> result = instance.maiorCircuito(pais, paises);
        assertEquals(expResult, result);
        
        System.out.println("Circuito Completo a partir do brasil: \n"+result);
        System.out.println("\n------------------------------------------------------\n");
    }
}
