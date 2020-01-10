/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixgraph;

import esinf_2nd_project.Mapa;
import esinf_2nd_project.Pais;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berna
 */
public class MatrixAlgorithmsTest {
    
    public MatrixAlgorithmsTest() {
    }

    /**
     * Test of transitiveClosure method, of class MatrixAlgorithms.
     */
    @Test
    public void testTransitiveClosure() {
        AdjacencyMatrix completeMap2 = new AdjacencyMatrix();
        
        Mapa m = new Mapa();
        m.reader.PaisReader("paises.txt");
        Pais p1 = m.getPaisByNome("moldavia");
        Pais p2 = m.getPaisByNome("turquia");
        Pais p3 = m.getPaisByNome("grecia");
        Pais p4 = m.getPaisByNome("holanda");
        
        completeMap2.insertVertex(p1);
        completeMap2.insertVertex(p2);
        completeMap2.insertVertex(p3);
        completeMap2.insertVertex(p4);

        completeMap2.insertEdge(p1, p2, 2.2);
        completeMap2.insertEdge(p2, p1, 2.2);
        completeMap2.insertEdge(p2, p4, 9.3);
        completeMap2.insertEdge(p4, p2, 9.3);
        completeMap2.insertEdge(p2, p3, 2.3);
        completeMap2.insertEdge(p3, p2, 2.3);
        completeMap2.insertEdge(p3, p4, 10.3);
        completeMap2.insertEdge(p4, p3, 10.3);

        MatrixAlgorithms.transitiveClosure(completeMap2, null);

        boolean teste = true;
        int i = 0, j = 0;
        while (i < completeMap2.numVertices && teste == true) {
            while (j < completeMap2.numVertices && teste == true) {
                if (i != j) {
                    if (completeMap2.getEdge(completeMap2.vertices.get(i), completeMap2.vertices.get(j)) == null) {
                        teste = false;
                    }
                }
                j++;
            }
            i++;
        }
        
        System.out.println("Teste: transitiveClosure");
        System.out.println(completeMap2);

        assertTrue(teste);
    }
    
}