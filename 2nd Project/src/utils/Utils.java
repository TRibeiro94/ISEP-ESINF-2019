package utils;

import java.text.DecimalFormat;

/**
 * @author Bernardo
 * @author Tiago
 */
public class Utils {
    
    /**
     * Calcula a distância entre dois pontos definidos pelas suas coordenadas
     * (shortest distance over the earth’s surface)
     * Consultar: https://www.movable-type.co.uk/scripts/latlong.html
     * @param lat1 latitude do primeiro ponto
     * @param lon1 longitude do primeiro ponto
     * @param lat2 latitude do segundo ponto
     * @param lon2 longitude do segundo ponto
     * @return distancia em metros entre dois pontos
     */
    public static double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371e3;
        double x = lat2 - lat1;
        double y = lon2 - lon1;
        double theta1 = Math.toRadians(lat1);
        double theta2 = Math.toRadians(lat2);
        double deltaTheta = Math.toRadians(x);
        double deltaLambda = Math.toRadians(y);
        double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2) + Math.cos(theta1) * Math.cos(theta2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }
    
    /**
     * Método usado para display de distância em formato de kilómetros
     * @param meters distância em metros
     * @return distância em kilómetros
     */
    public static String metersToKm(double meters){
        DecimalFormat round = new DecimalFormat(".##");
        double km = meters * 0.001;
        return round.format(km) + " Km";
    }
}
