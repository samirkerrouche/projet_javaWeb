package Mapping;
// Generated 8 févr. 2020 15:26:40 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;

/**
 * Programme generated by hbm2java
 */
public class Programme  implements java.io.Serializable {


     private Integer codeprog;
     private String nomprog;
     private Boolean isstandard;
     private Set profils = new HashSet(0);
     private Set affecters = new HashSet(0);
     private Set seances = new HashSet(0);

    public Programme() {
    }

    public Programme(String nomprog, Boolean isstandard, Set profils, Set affecters, Set seances) {
       this.nomprog = nomprog;
       this.isstandard = isstandard;
       this.profils = profils;
       this.affecters = affecters;
       this.seances = seances;
    }
   
    public Integer getCodeprog() {
        return this.codeprog;
    }
    
    public void setCodeprog(Integer codeprog) {
        this.codeprog = codeprog;
    }
    public String getNomprog() {
        return this.nomprog;
    }
    
    public void setNomprog(String nomprog) {
        this.nomprog = nomprog;
    }
    public Boolean getIsstandard() {
        return this.isstandard;
    }
    
    public void setIsstandard(Boolean isstandard) {
        this.isstandard = isstandard;
    }
    public Set getProfils() {
        return this.profils;
    }
    
    public void setProfils(Set profils) {
        this.profils = profils;
    }
    public Set getAffecters() {
        return this.affecters;
    }
    
    public void setAffecters(Set affecters) {
        this.affecters = affecters;
    }
    public Set getSeances() {
        return this.seances;
    }
    
    public void setSeances(Set seances) {
        this.seances = seances;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.codeprog);
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
        final Programme other = (Programme) obj;
        if (!Objects.equals(this.codeprog, other.codeprog)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Programme{" + "codeprog=" + codeprog + ", nomprog=" + nomprog + ", isstandard=" + isstandard + '}';
    }
    
    
}


