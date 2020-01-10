/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf_3rd_project;

import esinf_3rd_project.Mapa;
import esinf_3rd_project.BST;
import esinf_3rd_project.Pais_Comparable_NFronteiras_Populacao;
import esinf_3rd_project.Pais;
import esinf_3rd_project.Kd_tree;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Ribeiro
 */
public class Kd_treeTest {
    
    public Kd_treeTest() {
    }
    
    @Test
    public void testGetLevel(){
        System.out.println("Teste: getLevel");
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
        
        int expResult1 = 1; //nivel da root deve ser 1, é o primeiro nivel
        int result1 = instance.arvore_2d.getLevel(instance.arvore_2d.root().getElement());
        
        int expResult2 = 2; //nivel de qualquer children da root deve ser 2
        int result2 = instance.arvore_2d.getLevel(instance.arvore_2d.root().getLeft().getElement());
        int result2_1 = instance.arvore_2d.getLevel(instance.arvore_2d.root().getRight().getElement());
        
        int expResult3 = 3;  //nivel dos children do nodeChildren da root deve ser 3
        int result3 = instance.arvore_2d.getLevel(instance.arvore_2d.root().getLeft().getLeft().getElement());
        int result3_1 = instance.arvore_2d.getLevel(instance.arvore_2d.root().getLeft().getRight().getElement());
        
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2, result2_1);
        assertEquals(expResult3, result3, result3_1);
        System.out.println("\n");
    }
    /**
     * Test of insert method, of class Kd_tree.
     */
    @Test
    public void testInsert() {
        System.out.println("Teste: insert");
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
        System.out.println("\n Arvore 2D Teste:\n------------------------------\n" +instance.arvore_2d);
        System.out.println("\n");
    }

    /**
     * Test of equals method, of class Kd_tree.
     */
    @Test
    public void testEquals_Object() {
        System.out.println("Teste: equals (equals objects)");
        Object otherObj = new Kd_tree();
        Kd_tree instance = new Kd_tree();
        boolean expResult = true;
        boolean result = instance.equals(otherObj);
        assertEquals(expResult, result);
        System.out.println("\n");
    }
    
    /**
     * Test of equals method, of class Kd_tree.
     */
    @Test
    public void testEquals_Object2() {
        System.out.println("Teste: equals (different objects)");
        Object otherObj = null;
        Kd_tree instance = new Kd_tree();
        boolean expResult = false;
        boolean result = instance.equals(otherObj);
        assertEquals(expResult, result);
        System.out.println("\n");
    }

    /**
     * Test of equals method, of class Kd_tree.
     */
    @Test
    public void testEquals_BSTNode_BSTNode() {
        System.out.println("Teste: equals (Two nodes)");
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
        
        BST.Node<Pais> root1 = instance.arvore_2d.root;
        BST.Node<Pais> root2 = root1.getRight();

        boolean expResult = false;
        boolean result = instance.arvore_2d.equals(root1, root2);
        assertEquals(expResult, result);
        System.out.println("\n");
    }
}
