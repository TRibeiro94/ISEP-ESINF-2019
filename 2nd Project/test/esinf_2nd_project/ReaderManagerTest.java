package esinf_2nd_project;

import esinf_2nd_project.Pais;
import esinf_2nd_project.Mapa;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bernardo
 */
public class ReaderManagerTest {
    
    public ReaderManagerTest() {
    }

    /**
     * Test of PaisReader method, of class ReaderManager.
     */
    @Test
    public void testPaisReader() {
        System.out.println("\nTeste: Leitor de países\n--------------------------------------------");
        String fileName = "paises.txt";
        Mapa m = new Mapa();
        m.reader.PaisReader(fileName);
        
        int numEsperado = 59;                         //número de países no ficheiro .txt
        int paisesNoFicheiro = m.grafo.numVertices(); //depois da leitura, quantidade de países no grafo
        
        for(Pais p: m.grafo.vertices()){
            System.out.printf("País: %14s\tCapital: %s\n", p.getNome(), p.getCapital());
        }
        assertEquals(numEsperado, paisesNoFicheiro);
    }

    /**
     * Test of FronteirasReader method, of class ReaderManager.
        System.out.println("Teste: Leitor de Edges\n--------------------------------------------");
        String fileName = "fronteiras.txt";
     */
    @Test
    public void testFronteirasReader() {
        System.out.println("\nTeste: Leitor de Edges\n--------------------------------------------");
        String fileName = "fronteiras.txt";
        Mapa m = new Mapa();
        m.reader.PaisReader("paises.txt");
        m.reader.FronteirasReader(fileName);
        
        int esperado = 226;             //número de ramos no ficheiro .txt
        int n = m.grafo.numEdges();     //depois da leitura, a quantidade de ramos que está em numEdges
        ArrayList<Pais>[] arrayAdjacentes = new ArrayList[m.grafo.numVertices()];
         
        for (int u = 0; u < m.grafo.numVertices(); u++) {
            arrayAdjacentes[u] = new ArrayList();
            for (Pais p : m.grafo.adjVertices(m.getPaisByKey(u))) {
                arrayAdjacentes[u].add(p);
            }
        }
        int index = 0;
        for (ArrayList<Pais> lista : arrayAdjacentes) {
            System.out.println(m.getPaisByKey(index).getNome() + " - " + lista);
            index++;
        }
        
        assertEquals(esperado, n);
    }
}
