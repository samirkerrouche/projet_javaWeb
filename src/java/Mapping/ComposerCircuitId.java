package Mapping;
// Generated 8 févr. 2020 15:26:40 by Hibernate Tools 4.3.1



/**
 * ComposerCircuitId generated by hbm2java
 */
public class ComposerCircuitId  implements java.io.Serializable {


     private int codeexo;
     private int codecir;

    public ComposerCircuitId() {
    }

    public ComposerCircuitId(int codeexo, int codecir) {
       this.codeexo = codeexo;
       this.codecir = codecir;
    }
   
    public int getCodeexo() {
        return this.codeexo;
    }
    
    public void setCodeexo(int codeexo) {
        this.codeexo = codeexo;
    }
    public int getCodecir() {
        return this.codecir;
    }
    
    public void setCodecir(int codecir) {
        this.codecir = codecir;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ComposerCircuitId) ) return false;
		 ComposerCircuitId castOther = ( ComposerCircuitId ) other; 
         
		 return (this.getCodeexo()==castOther.getCodeexo())
 && (this.getCodecir()==castOther.getCodecir());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCodeexo();
         result = 37 * result + this.getCodecir();
         return result;
   }   


}


