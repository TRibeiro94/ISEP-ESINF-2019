package utils;

import esinf_2nd_project.Pais;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Bernardo
 * @author Tiago
 */
public class UtilsTest {
    
    public UtilsTest() {
    }

    /**
     * Test of calcularDistancia method, of class Utils.
     */
    @Test
    public void testCalcularDistancia() {
        System.out.println("Teste: calcularDistancia");
        Pais p1 = new Pais("grecia", "europa", 10.76, "atenas", 37.97918, 23.716647);
        Pais p2 = new Pais("turquia", "europa", 79.81, "ancara", 39.9198700, 32.8542700);
        double lat1 = p1.getLatitude();
        double lon1 = p1.getLongitude();
        double lat2 = p2.getLatitude();
        double lon2 = p2.getLongitude();
        double expResult = 818696.4569905766;
        double result = Utils.calcularDistancia(lat1, lon1, lat2, lon2);
        System.out.println("\nCálculo da distância entre Grécia e Turquia = "+ Utils.calcularDistancia(lat1, lon1, lat2, lon2) + " m");
        System.out.println("Distância esperada: " + expResult + " m");
        assertEquals(expResult, result, 0.0);
        System.out.println("\n------------------------------------------------------\n");
    }

    /**
     * Test of metersToKm method, of class Utils.
     */
    @Test
    public void testMetersToKm() {
        System.out.println("Teste: metersToKm");
        double meters = 818696.4569905766;
        String expResult = "818,7 Km";
        System.out.println("Distância em metros: " + meters + " m");
        System.out.println("Distância em kilómetros: " + expResult);
        String result = Utils.metersToKm(meters);
        assertEquals(expResult, result);
        System.out.println("\n------------------------------------------------------\n");
    }
}
