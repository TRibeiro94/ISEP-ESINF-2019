package esinf_2nd_project;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.exit;
import java.util.NoSuchElementException;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author Tiago Ribeiro
 * @author Bernardo Carvalho
 */
public class ReaderManager {

    // agregação da classe central
    private Mapa mapa;

    public ReaderManager(Mapa mapa){
        this.mapa = mapa;
    }
    
    /**
     * Método utilizado para ler os países de um ficheiro .txt e adicionar as mesmas ao grafo da classe central
     * @param fileName nome do ficheiro .txt
     */
    public void PaisReader(String fileName) {
        try {
            Scanner reader = new Scanner(new File(fileName));
            reader.useDelimiter(",|\n");

            Pais p = null;

            try {
                while (reader.hasNext()) {
                    String nome = reader.next().trim();
                    String continente = reader.next().trim();
                    String populacao = reader.next().trim();
                    String capital = reader.next().trim();
                    String latitude = reader.next().trim();
                    String longitude = reader.next().trim();

                    p = new Pais(nome, continente, Double.parseDouble(populacao), capital, Double.parseDouble(latitude), Double.parseDouble(longitude));
                    mapa.grafo.insertVertex(p);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Erro na leitura do ficheiro Países. Por favor verifique o ficheiro '.txt' em causa.");
                e.printStackTrace();
                exit(0);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("O ficheiro de 'Países' especificado não existe. Por favor certifique-se da sua existência.");
            e.printStackTrace();
            exit(0);
        }
    }

    /**
     * Método utilizado para ler as fronteiras de um ficheiro .txt e adicionar as mesmas como ramos do grafo entre os países
     * @param fileName nome do ficheiro .txt
     */
    public void FronteirasReader(String fileName) {
            try {
                Scanner reader = new Scanner(new File(fileName));
                reader.useDelimiter(",|\n");

                try {
                    while (reader.hasNext()) {
                        String nomePais1 = reader.next().trim();
                        String nomePais2 = reader.next().trim();

                        Pais p1 = mapa.getPaisByNome(nomePais1);
                        Pais p2 = mapa.getPaisByNome(nomePais2);

                        double distancia = Utils.calcularDistancia(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
                        mapa.addFronteira(p1, p2, distancia);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Erro na leitura do ficheiro Fronteiras. Por favor verifique o ficheiro '.txt' em causa.");
                    e.printStackTrace();
                    exit(0);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("O ficheiro de 'Fronteiras' especificado não existe. Por favor certifique-se da sua existência.");
                e.printStackTrace();
                exit(0);
            }
    }
}
