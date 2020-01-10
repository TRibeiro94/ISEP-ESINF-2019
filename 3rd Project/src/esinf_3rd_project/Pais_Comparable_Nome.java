/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf_3rd_project;


/**
 *
 * @author Bernardo Carvalho
 * @author Tiago Ribeiro
 */
public class Pais_Comparable_Nome extends Pais {
    
    /**
     * Construtor de uma instância de País_Comparable_Nome que recebe por parâmetro
     * @param nome nome do País
     * @param continente continente a que pertence
     * @param populacao população do país
     * @param capital capital do país
     * @param latitude latitude do país
     * @param longitude longitude do país
     */
    public Pais_Comparable_Nome(String nome, String continente, double populacao, String capital, double latitude, double longitude){
        super(nome, continente, populacao, capital, latitude, longitude);
    }
    
    /**
     * Construtor de uma instância de País_Comparable_Nome que recebe por parâmetro
     * @param nome nome do País
     */
    public Pais_Comparable_Nome(String nome){
        super(nome);
    }
    
    /**
     * Construtor de uma instância de País_Comparable_Nome com valores por omissão
     */
    public Pais_Comparable_Nome(){
        super();
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    /**
     * Compara dois objectos Pais através do nome, Continente, Populacao, Capital, Latitude e Longitude.
     * Dendo os objectos considerados iguais apenas quando estas 6 características forem iguais.
     * @param obj objecto que vai ser comparado com o objecto que chama o método
     * @return true, se as referências dos objectos a ser comparados apontam para o mesmo objecto
     *         false, se o objecto comparado for nulo ou as classes dos dois objectos forem diferentes
     *         true, se os 6 atributos dos objectos forem iguais
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Pais o) {
        return this.getNome().compareTo(o.getNome());
    }
    
}
