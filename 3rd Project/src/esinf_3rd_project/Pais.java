package esinf_3rd_project;

import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Bernardo Carvalho
 * @author Tiago Ribeiro
 */
public abstract class Pais implements Comparable<Pais>{
    
    // Variáveis de instância
    private String nome;
    private String continente;
    private double populacao;
    private String capital;
    private double latitude;
    private double longitude;
    private LinkedList<String> fronteiras;
    private int numFronteiras;
    
    // Constantes 
    private static final String NOME_POR_OMISSAO = "sem nome";
    private static final String CONTINENTE_POR_OMISSAO = "nao definido";
    private static final Double POP_POR_OMISSAO = 0.0;
    private static final String CAPITAL_POR_OMISSAO = "nao definida";
    private static final Double LAT_POR_OMISSAO = 0.0;
    private static final Double LON_POR_OMISSAO = 0.0;
    
    private final LinkedList<String> FRONTEIRAS_POR_OMISSAO = new LinkedList();
    private final int NUM_FRONTEIRAS_POR_OMISSAO = 0;
    
    public Pais(String nome, String continente, double populacao, String capital, double latitude, double longitude){
        this.nome = nome;
        this.continente = continente;
        this.populacao = populacao;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fronteiras = FRONTEIRAS_POR_OMISSAO;
        this.numFronteiras = NUM_FRONTEIRAS_POR_OMISSAO;
    }
    
    public Pais(String nome){
        this.nome = nome;
        continente = CONTINENTE_POR_OMISSAO;
        populacao = POP_POR_OMISSAO;
        capital = CAPITAL_POR_OMISSAO;
        latitude = LAT_POR_OMISSAO;
        longitude = LON_POR_OMISSAO;
        fronteiras = FRONTEIRAS_POR_OMISSAO;
        numFronteiras = NUM_FRONTEIRAS_POR_OMISSAO;
    }
    
    public Pais(Double latitude, Double longitude){
        nome = NOME_POR_OMISSAO;
        continente = CONTINENTE_POR_OMISSAO;
        populacao = POP_POR_OMISSAO;
        capital = CAPITAL_POR_OMISSAO;
        this.latitude = latitude;
        this.longitude = longitude;
        fronteiras = FRONTEIRAS_POR_OMISSAO;
        numFronteiras = NUM_FRONTEIRAS_POR_OMISSAO;
    }
    
    public Pais(){
        nome = NOME_POR_OMISSAO;
        continente = CONTINENTE_POR_OMISSAO;
        populacao = POP_POR_OMISSAO;
        capital = CAPITAL_POR_OMISSAO;
        latitude = LAT_POR_OMISSAO;
        longitude = LON_POR_OMISSAO;
        fronteiras = FRONTEIRAS_POR_OMISSAO;
        numFronteiras = NUM_FRONTEIRAS_POR_OMISSAO;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the continente
     */
    public String getContinente() {
        return continente;
    }

    /**
     * @return the populacao
     */
    public double getPopulacao() {
        return populacao;
    }

    /**
     * @return the capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }
    
    /**
     * @return the fronteiras
     */
    public LinkedList<String> getFronteiras() {
        return fronteiras;
    }
    
    /**
     * @return the numFronteiras
     */
    public int getNumFronteiras() {
        return numFronteiras;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param continente the continente to set
     */
    public void setContinente(String continente) {
        this.continente = continente;
    }

    /**
     * @param populacao the populacao to set
     */
    public void setPopulacao(double populacao) {
        this.populacao = populacao;
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    /**
     * @param fronteiras the fronteiras to set
     */
    public void setFronteiras(LinkedList<String> fronteiras) {
        this.fronteiras = fronteiras;
    }

    /**
     * @param numFronteiras the numFronteiras to set
     */
    public void setNumFronteiras(int numFronteiras) {
        this.numFronteiras = numFronteiras;
    }
    
    @Override
    public String toString(){
        return String.format(this.getNome());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.continente);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.populacao) ^ (Double.doubleToLongBits(this.populacao) >>> 32));
        hash = 23 * hash + Objects.hashCode(this.capital);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Pais outroPais = (Pais) obj;
        return this.nome.equalsIgnoreCase(outroPais.nome)
                && this.continente.equalsIgnoreCase(outroPais.continente)
                && this.populacao==outroPais.populacao
                && this.capital.equalsIgnoreCase(outroPais.capital)
                && this.latitude==(outroPais.latitude)
                && this.longitude==(outroPais.longitude);
    }

    @Override
    public abstract int compareTo(Pais o);
}
