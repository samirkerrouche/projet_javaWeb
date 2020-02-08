package Mapping;
// Generated 8 févr. 2020 15:26:40 by Hibernate Tools 4.3.1



/**
 * ExecuterCircuitId generated by hbm2java
 */
public class ExecuterCircuitId  implements java.io.Serializable {


     private int codeoccs;
     private int codecir;
     private int codecli;

    public ExecuterCircuitId() {
    }

    public ExecuterCircuitId(int codeoccs, int codecir, int codecli) {
       this.codeoccs = codeoccs;
       this.codecir = codecir;
       this.codecli = codecli;
    }
   
    public int getCodeoccs() {
        return this.codeoccs;
    }
    
    public void setCodeoccs(int codeoccs) {
        this.codeoccs = codeoccs;
    }
    public int getCodecir() {
        return this.codecir;
    }
    
    public void setCodecir(int codecir) {
        this.codecir = codecir;
    }
    public int getCodecli() {
        return this.codecli;
    }
    
    public void setCodecli(int codecli) {
        this.codecli = codecli;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ExecuterCircuitId) ) return false;
		 ExecuterCircuitId castOther = ( ExecuterCircuitId ) other; 
         
		 return (this.getCodeoccs()==castOther.getCodeoccs())
 && (this.getCodecir()==castOther.getCodecir())
 && (this.getCodecli()==castOther.getCodecli());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCodeoccs();
         result = 37 * result + this.getCodecir();
         result = 37 * result + this.getCodecli();
         return result;
   }   


}

