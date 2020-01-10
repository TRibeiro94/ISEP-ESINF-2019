/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf_3rd_project;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.exit;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Bernardo Carvalho
 * @author Tiago Ribeiro
 */
public class Reader_Manager {
    
    // agregação da classe central
    private Mapa mapa;
    
    public Reader_Manager(Mapa mapa){
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

            Pais p_nome = null;
            Pais p_nFronteiras_populacao = null;

            try {
                while (reader.hasNext()) {
                    String nome = reader.next().trim();
                    String continente = reader.next().trim();
                    String populacao = reader.next().trim();
                    String capital = reader.next().trim();
                    String latitude = reader.next().trim();
                    String longitude = reader.next().trim();

                    p_nome = new Pais_Comparable_Nome(nome, continente, Double.parseDouble(populacao), capital, Double.parseDouble(latitude), Double.parseDouble(longitude));
                    p_nFronteiras_populacao = new Pais_Comparable_NFronteiras_Populacao(nome, continente, Double.parseDouble(populacao), capital, Double.parseDouble(latitude), Double.parseDouble(longitude));
                    
                    mapa.mapa_paises_nome.put(nome, p_nome);
                    mapa.mapa_paises.put(nome, p_nFronteiras_populacao);
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

                        mapa.addFronteiraArvore_Nome(nomePais1, nomePais2);
                        mapa.addFronteiraArvore_Nome(nomePais2, nomePais1);
                        
                        mapa.addFronteiraMapaPaises(nomePais1, nomePais2);
                        mapa.addFronteiraMapaPaises(nomePais2, nomePais1);
                    }
                    mapa.insertIntoArvore_Nome();
                    mapa.insertIntoArvore_NFronteiras_Populacao();
                    mapa.insertIntoArvore_KD();
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
