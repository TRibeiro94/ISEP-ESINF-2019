/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixgraph;

import esinf_2nd_project.Pais;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berna
 */
public class AdjacencyMatrixTest {
    
    public AdjacencyMatrixTest() {
    }

    /**
     * Test of privateGet method, of class AdjacencyMatrix.
     */
    @Test
    public void testPrivateGet() {
        System.out.println("privateGet");
        int x = 0;
        int y = 0;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        Double expResult = null;
        Double result = instance.privateGet(x, y);
        assertEquals(expResult, result);
    }

    /**
     * Test of privateSet method, of class AdjacencyMatrix.
     */
    @Test
    public void testPrivateSet() {
        System.out.println("privateSet");
        int x = 0;
        int y = 0;
        Double e = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        instance.privateSet(x, y, e);
    }

    /**
     * Test of toIndex method, of class AdjacencyMatrix.
     */
    @Test
    public void testToIndex() {
        System.out.println("toIndex");
        Pais vertex = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        int expResult = -1;
        int result = instance.toIndex(vertex);
        assertEquals(expResult, result);
    }

    /**
     * Test of numVertices method, of class AdjacencyMatrix.
     */
    @Test
    public void testNumVertices() {
        System.out.println("numVertices");
        AdjacencyMatrix instance = new AdjacencyMatrix();
        int expResult = 0;
        int result = instance.numVertices();
        assertEquals(expResult, result);
    }

    /**
     * Test of numEdges method, of class AdjacencyMatrix.
     */
    @Test
    public void testNumEdges() {
        System.out.println("numEdges");
        AdjacencyMatrix instance = new AdjacencyMatrix();
        int expResult = 0;
        int result = instance.numEdges();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkVertex method, of class AdjacencyMatrix.
     */
    @Test
    public void testCheckVertex() {
        System.out.println("checkVertex");
        Pais vertex = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        boolean expResult = false;
        boolean result = instance.checkVertex(vertex);
        assertEquals(expResult, result);
    }

    /**
     * Test of vertices method, of class AdjacencyMatrix.
     */
    @Test
    public void testVertices() {
        System.out.println("vertices");
        AdjacencyMatrix instance = new AdjacencyMatrix();
        Pais p = new Pais();
        instance.insertVertex(p);
        Iterable<Pais> expResult = instance.vertices();
        Iterable<Pais> result = instance.vertices();
        assertEquals(expResult, result);
    }

    /**
     * Test of edges method, of class AdjacencyMatrix.
     */
    @Test
    public void testEdges() {
        System.out.println("edges");
        AdjacencyMatrix instance = new AdjacencyMatrix();
        double d = 9.2;
        instance.insertEdge(0, 0, d);
        Iterable<Double> expResult = instance.edges();
        Iterable<Double> result = instance.edges();
        assertEquals(expResult, result);
    }

    /**
     * Test of outDegree method, of class AdjacencyMatrix.
     */
    @Test
    public void testOutDegree() {
        System.out.println("outDegree");
        Pais vertex = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        int expResult = -1;
        int result = instance.outDegree(vertex);
        assertEquals(expResult, result);
    }

    /**
     * Test of inDegree method, of class AdjacencyMatrix.
     */
    @Test
    public void testInDegree() {
        System.out.println("inDegree");
        Pais vertex = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        int expResult = -1;
        int result = instance.inDegree(vertex);
        assertEquals(expResult, result);
    }

    /**
     * Test of getEdge method, of class AdjacencyMatrix.
     */
    @Test
    public void testGetEdge() {
        System.out.println("getEdge");
        Pais vertexA = null;
        Pais vertexB = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        Double expResult = null;
        Double result = instance.getEdge(vertexA, vertexB);
        assertEquals(expResult, result);
    }

    /**
     * Test of endVertices method, of class AdjacencyMatrix.
     */
    @Test
    public void testEndVertices() {
        System.out.println("endVertices");
        Double edge = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        Pais[] expResult = null;
        Pais[] result = instance.endVertices(edge);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of insertVertex method, of class AdjacencyMatrix.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("insertVertex");
        Pais newVertex = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        boolean expResult = true;
        boolean result = instance.insertVertex(newVertex);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertEdge method, of class AdjacencyMatrix.
     */
    @Test
    public void testInsertEdge_3args_1() {
        System.out.println("insertEdge");
        int indexA = 0;
        int indexB = 0;
        Double newEdge = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        instance.insertEdge(indexA, indexB, newEdge);
    }

    /**
     * Test of insertEdge method, of class AdjacencyMatrix.
     */
    @Test
    public void testInsertEdge_3args_2() {
        System.out.println("insertEdge");
        Pais vertexA = new Pais();
        Pais vertexB = new Pais();
        Double newEdge = 4.0;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        boolean expResult = false;
        boolean result = instance.insertEdge(vertexA, vertexB, newEdge);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeVertex method, of class AdjacencyMatrix.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("removeVertex");
        Pais vertex = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        boolean expResult = false;
        boolean result = instance.removeVertex(vertex);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeEdge method, of class AdjacencyMatrix.
     */
    @Test
    public void testRemoveEdge_int_int() {
        System.out.println("removeEdge");
        int indexA = 0;
        int indexB = 0;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        Double expResult = null;
        Double result = instance.removeEdge(indexA, indexB);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeEdge method, of class AdjacencyMatrix.
     */
    @Test
    public void testRemoveEdge_Pais_Pais() {
        System.out.println("removeEdge");
        Pais vertexA = null;
        Pais vertexB = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        Double expResult = null;
        Double result = instance.removeEdge(vertexA, vertexB);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class AdjacencyMatrix.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AdjacencyMatrix instance = new AdjacencyMatrix();
        String expResult = instance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class AdjacencyMatrix.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object oth = null;
        AdjacencyMatrix instance = new AdjacencyMatrix();
        boolean expResult = false;
        boolean result = instance.equals(oth);
        assertEquals(expResult, result);
    }
    
}
