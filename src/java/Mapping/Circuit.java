package Mapping;
// Generated 8 févr. 2020 15:26:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Circuit generated by hbm2java
 */
public class Circuit  implements java.io.Serializable {


     private Integer codecir;
     private String nomcir;
     private Set seances = new HashSet(0);
     private Set executerCircuits = new HashSet(0);
     private Set composerCircuits = new HashSet(0);

    public Circuit() {
    }

    public Circuit(String nomcir, Set seances, Set executerCircuits, Set composerCircuits) {
       this.nomcir = nomcir;
       this.seances = seances;
       this.executerCircuits = executerCircuits;
       this.composerCircuits = composerCircuits;
    }
   
    public Integer getCodecir() {
        return this.codecir;
    }
    
    public void setCodecir(Integer codecir) {
        this.codecir = codecir;
    }
    public String getNomcir() {
        return this.nomcir;
    }
    
    public void setNomcir(String nomcir) {
        this.nomcir = nomcir;
    }
    public Set getSeances() {
        return this.seances;
    }
    
    public void setSeances(Set seances) {
        this.seances = seances;
    }
    public Set getExecuterCircuits() {
        return this.executerCircuits;
    }
    
    public void setExecuterCircuits(Set executerCircuits) {
        this.executerCircuits = executerCircuits;
    }
    public Set getComposerCircuits() {
        return this.composerCircuits;
    }
    
    public void setComposerCircuits(Set composerCircuits) {
        this.composerCircuits = composerCircuits;
    }


 @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

}


