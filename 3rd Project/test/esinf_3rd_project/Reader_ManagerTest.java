/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf_3rd_project;

import esinf_3rd_project.Mapa;
import esinf_3rd_project.Pais;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiago Ribeiro
 * @author Bernardo Carvalho
 */
public class Reader_ManagerTest {
    
    public Reader_ManagerTest() {
    }

    /**
     * Test of PaisReader method, of class Reader_Manager.
     */
    @Test
    public void testPaisReader() {
        System.out.println("Teste: PaisReader");
        System.out.println("\n   Leitor de países a partir da árvore AVL\n----------------------------------------------");
        String fileName = "paises.txt";
        Mapa instance = new Mapa();
        instance.reader.PaisReader(fileName);
        int expResult = 59;
        int result = 0;
        for(Pais p : instance.mapa_paises.values()){
            result++;
            System.out.printf("País: %14s  |  Capital: %12s\n", p.getNome(), p.getCapital());
        }
        assertEquals(expResult, result);
        System.out.println("----------------------------------------------\n");
    }

    /**
     * Test of FronteirasReader method, of class Reader_Manager.
     */
    @Test
    public void testFronteirasReader() {
        System.out.println("Teste: FronteirasReader");
        System.out.println("\n    Leitor de fronteiras dos países\n----------------------------------------------");
        String fileName = "fronteiras.txt";
        
        Mapa instance = new Mapa();
        instance.reader.PaisReader("paises.txt");
        instance.reader.FronteirasReader(fileName);
        
        LinkedList<String> expResult = new LinkedList<>();
        expResult.add("franca");
        expResult.add("portugal");
        LinkedList<String> result = instance.listaFronteiras("espanha");
        assertEquals(expResult, result);
        
        for(Pais p : instance.arvore_nome.inOrder()){
            System.out.printf("%14s", p.getNome());
            System.out.printf(" - " + p.getFronteiras() + "\n");
        }
        
        System.out.println("----------------------------------------------\n\n\t\t  AVL TREE:\n----------------------------------------------\n" + instance.arvore_nome + "----------------------------------------------\n");
        System.out.println("----------------------------------------------\n\n\t\t  2D TREE:\n---------------------------------------------- \n" + instance.arvore_2d   + "----------------------------------------------\n");
    }
}
