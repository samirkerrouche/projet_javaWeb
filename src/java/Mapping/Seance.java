package Mapping;
// Generated 8 févr. 2020 15:26:40 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Seance generated by hbm2java
 */
public class Seance implements java.io.Serializable {

    private Integer codeseance;
    private Circuit circuit;
    private Programme programme;
    private String nomseance;
    private Integer ordreseance;
    private Integer mintours;
    private Integer maxtours;
    private Double tempsrecup;
    private Double tempsexo;
    private Boolean isbilan;
    private Set composerSeances = new HashSet(0);
    private Set occurrenceSs = new HashSet(0);

    public Seance() {
    }

    public Seance(Circuit circuit, Programme programme, String nomseance, Integer ordreseance, Integer mintours, Integer maxtours, Double tempsrecup, Double tempsexo,Boolean isbilan, Set composerSeances, Set occurrenceSs) {
        this.circuit = circuit;
        this.programme = programme;
        this.nomseance = nomseance;
        this.ordreseance = ordreseance;
        this.mintours = mintours;
        this.maxtours = maxtours;
        this.tempsrecup = tempsrecup;
        this.tempsexo = tempsexo;
        this.isbilan = isbilan;
        this.composerSeances = composerSeances;
        this.occurrenceSs = occurrenceSs;
    }

    public Integer getMintours() {
        return mintours;
    }

    public void setMintours(Integer mintours) {
        this.mintours = mintours;
    }

    public Integer getMaxtours() {
        return maxtours;
    }

    public void setMaxtours(Integer maxtours) {
        this.maxtours = maxtours;
    }

    public Integer getCodeseance() {
        return this.codeseance;
    }

    public void setCodeseance(Integer codeseance) {
        this.codeseance = codeseance;
    }

    public Circuit getCircuit() {
        return this.circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Programme getProgramme() {
        return this.programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public String getNomseance() {
        return this.nomseance;
    }

    public Double getTempsrecup() {
        return tempsrecup;
    }

    public void setTempsrecup(Double tempsrecup) {
        this.tempsrecup = tempsrecup;
    }

    public Double getTempsexo() {
        return tempsexo;
    }

    public void setTempsexo(Double tempsexo) {
        this.tempsexo = tempsexo;
    }

    public void setNomseance(String nomseance) {
        this.nomseance = nomseance;
    }

    public Boolean getIsbilan() {
        return this.isbilan;
    }

    public void setIsbilan(Boolean isbilan) {
        this.isbilan = isbilan;
    }

    public Set getComposerSeances() {
        return this.composerSeances;
    }

    public void setComposerSeances(Set composerSeances) {
        this.composerSeances = composerSeances;
    }

    public Set getOccurrenceSs() {
        return this.occurrenceSs;
    }

    public Integer getOrdreseance() {
        return ordreseance;
    }

    public void setOrdreseance(Integer ordreseance) {
        this.ordreseance = ordreseance;
    }

    public void setOccurrenceSs(Set occurrenceSs) {
        this.occurrenceSs = occurrenceSs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.codeseance);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Seance other = (Seance) obj;
        if (!Objects.equals(this.codeseance, other.codeseance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Seance{" + "codeseance=" + codeseance + ", nomseance=" + nomseance + ", isbilan=" + isbilan + '}';
    }

}
