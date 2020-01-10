package esinf_1st_project;

import esinf_1st_project.Pais;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author berna
 */
public class PaisTest {
    
    public PaisTest() {
    }

    /**
     * Test of getNome method, of class Pais.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        String expResult = "Portugal";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContinente method, of class Pais.
     */
    @Test
    public void testGetContinente() {
        System.out.println("getContinente");
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        String expResult = "europa";
        String result = instance.getContinente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPopulacao method, of class Pais.
     */
    @Test
    public void testGetPopulacao() {
        System.out.println("getPopulacao");
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        double expResult = 10;
        double result = instance.getPopulacao();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCapital method, of class Pais.
     */
    @Test
    public void testGetCapital() {
        System.out.println("getCapital");
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        String expResult = "Lisboa";
        String result = instance.getCapital();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Pais.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        double expResult = 2;
        double result = instance.getLatitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLongitude method, of class Pais.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        double expResult = 4;
        double result = instance.getLongitude();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setNome method, of class Pais.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "Espanha";
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        instance.setNome(nome);
    }

    /**
     * Test of setContinente method, of class Pais.
     */
    @Test
    public void testSetContinente() {
        System.out.println("setContinente");
        String continente = "américa do sul";
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        instance.setContinente(continente);
    }

    /**
     * Test of setPopulacao method, of class Pais.
     */
    @Test
    public void testSetPopulacao() {
        System.out.println("setPopulacao");
        double populacao = 100;
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        instance.setPopulacao(populacao);
    }

    /**
     * Test of setCapital method, of class Pais.
     */
    @Test
    public void testSetCapital() {
        System.out.println("setCapital");
        String capital = "Porto";
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        instance.setCapital(capital);
    }

    /**
     * Test of setLatitude method, of class Pais.
     */
    @Test
    public void testSetLatitude() {
        System.out.println("setLatitude");
        double latitude = 9;
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        instance.setLatitude(latitude);
    }

    /**
     * Test of setLongitude method, of class Pais.
     */
    @Test
    public void testSetLongitude() {
        System.out.println("setLongitude");
        double longitude = 3.9;
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        instance.setLongitude(longitude);
    }

    /**
     * Test of toString method, of class Pais.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Pais instance = new Pais("Portugal", "europa", 10, "Lisboa", 2, 4);
        String expResult = "País: Portugal, Continente: europa, População: 10,00, Capital: Lisboa, Lat: 2,000000, Lon: 4,000000";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Pais.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Pais p1 = new Pais("espanha", "europa", 100, "madrid", 7, 1);
        Pais p2 = new Pais("espanha", "europa", 100, "madrid", 7, 1);
        Assert.assertTrue(p1.equals(p2) && p2.equals(p1));
        Assert.assertTrue(p1.hashCode() == p2.hashCode());
    }

    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals() {
        System.out.println("equals 1 : mesma referência");
        Object outroObjecto = new Pais();
        Pais instance = new Pais();
        instance = (Pais)outroObjecto;
        boolean expResult = true;
        boolean result = instance.equals(outroObjecto);
        assertEquals(expResult, result);
    }
    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals 2 : objecto null");
        Object outroObjecto = null;
        Pais instance = new Pais();
        boolean expResult = false;
        boolean result = instance.equals(outroObjecto);
        assertEquals(expResult, result);
    }
    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals3(){
        System.out.println("equals 3: objectos de classes diferentes");
        Object outroObjecto = new Object();
        Pais instance = new Pais();
        boolean expResult = false;
        boolean result = instance.equals(outroObjecto);
        assertEquals(expResult, result);
    }
    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals4(){
        System.out.println("equals 4: objectos por omissão iguais");
        Object outroObjecto = new Pais();
        Pais instance = new Pais();
        boolean expResult = true;
        boolean result = instance.equals(outroObjecto);
        assertEquals(expResult, result);
    }
    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals5(){
        System.out.println("equals 5: objectos com todos os atributos iguais");
        Object outroObjecto = new Pais("Portugal","Europa",10.31,"Lisboa",35,-55);
        Pais instance = new Pais("Portugal","Europa",10.31,"Lisboa",35,-55);
        boolean expResult = true;
        boolean result = instance.equals(outroObjecto);
        assertEquals(expResult, result);
    }
}
