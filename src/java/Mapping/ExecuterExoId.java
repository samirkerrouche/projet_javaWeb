package Mapping;
// Generated 8 févr. 2020 15:26:40 by Hibernate Tools 4.3.1



/**
 * ExecuterExoId generated by hbm2java
 */
public class ExecuterExoId  implements java.io.Serializable {


     private int codeexo;
     private int codecli;
     private int codeoccs;

    public ExecuterExoId() {
    }

    public ExecuterExoId(int codeexo, int codecli, int codeoccs) {
       this.codeexo = codeexo;
       this.codecli = codecli;
       this.codeoccs = codeoccs;
    }
   
    public int getCodeexo() {
        return this.codeexo;
    }
    
    public void setCodeexo(int codeexo) {
        this.codeexo = codeexo;
    }
    public int getCodecli() {
        return this.codecli;
    }
    
    public void setCodecli(int codecli) {
        this.codecli = codecli;
    }
    public int getCodeoccs() {
        return this.codeoccs;
    }
    
    public void setCodeoccs(int codeoccs) {
        this.codeoccs = codeoccs;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ExecuterExoId) ) return false;
		 ExecuterExoId castOther = ( ExecuterExoId ) other; 
         
		 return (this.getCodeexo()==castOther.getCodeexo())
 && (this.getCodecli()==castOther.getCodecli())
 && (this.getCodeoccs()==castOther.getCodeoccs());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getCodeexo();
         result = 37 * result + this.getCodecli();
         result = 37 * result + this.getCodeoccs();
         return result;
   }   


}

